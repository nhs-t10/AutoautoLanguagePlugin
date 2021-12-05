package net.coleh.autoautolanguageplugin.gotoreference;

import com.intellij.model.SymbolResolveResult;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;

import net.coleh.autoautolanguageplugin.NotificationShowerHelper;
import net.coleh.autoautolanguageplugin.parse.AutoautoFuncDefStatement;
import net.coleh.autoautolanguageplugin.parse.AutoautoLetStatement;
import net.coleh.autoautolanguageplugin.parse.AutoautoPsiUtilImpl;
import net.coleh.autoautolanguageplugin.parse.AutoautoVariableReference;
import net.coleh.autoautolanguageplugin.parse.impl.AutoautoVariableReferenceImpl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public class AutoautoPsiReference extends PsiReferenceBase<PsiElement> {
    private final AutoautoVariableReference node;
    public String filename;
    public PsiFile file;
    String name;
    public AutoautoPsiReference(AutoautoVariableReference node) {
        super(node, false);
        this.node = node;
        this.name = node.getName();
        this.file = node.getContainingFile();
        this.filename = file.getName();
    }

    @NotNull
    @Override
    public PsiElement getElement() {
        return node;
    }

    @NotNull
    @Override
    //TODO: make this work. It's an experimental API, so it doesn't need to work for now (written December 2021)
    public Collection<? extends SymbolResolveResult> resolveReference() {
        return new ArrayList<>();
    }

    @NotNull
    @Override
    public TextRange getRangeInElement() {
        return new TextRange(0,node.getTextLength());
    }

    @Nullable
    @Override
    public PsiNamedElement resolve() {
        NotificationShowerHelper.showNotif("DEBUG", "They tried to resolve it", "resolving...", node.getProject());

        Collection<AutoautoLetStatement> letStatements = PsiTreeUtil.findChildrenOfType(file, AutoautoLetStatement.class);
        for (AutoautoLetStatement s : letStatements) {
            if (s.getName().equals(name)) return s;
        }
        if(node.getBaseExpressionType() == AutoautoPsiUtilImpl.BaseExpressionType.FUNCTION_CALL) {
            //if it's a function, look for functions...
            Collection<AutoautoFuncDefStatement> functionDefStatements = PsiTreeUtil.findChildrenOfType(file, AutoautoFuncDefStatement.class);
            for (AutoautoFuncDefStatement s : functionDefStatements) {
                if (s.getName().equals(name)) return s;
            }
            //no items-- therefore, must be built-in (or null)
            return JavaMethodFinder.getByName(node.getProject(), name);
        }

        NotificationShowerHelper.showNotif("DEBUG", "It resolved to null", "resolve = null", node.getProject());
        return null;
    }

    @NotNull
    @Override
    public String getCanonicalText() {
        return filename + "#" + name;
    }

    @Override
    public PsiElement handleElementRename(@NotNull String newElementName) throws IncorrectOperationException {
        node.getFirstChild().replace(PsiMakerHelper.makeIdentifier(node.getProject(), newElementName));
        this.name = newElementName;
        return node;
    }

    @Override
    public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
        if(element instanceof PsiNamedElement) {
            handleElementRename(((PsiNamedElement)element).getName());
            return node;
        }
        throw new IncorrectOperationException("Attempt to point to a non-named element");
    }

    @Override
    public boolean isReferenceTo(@NotNull PsiElement element) {
        return resolve().equals(element);
    }

    @Override
    public boolean isSoft() {
        return false;
    }

    @Override
    public String toString() {
        PsiNamedElement r = resolve();
        return "ref:" + getCanonicalText() + "(" + (r == null ? null : r.getTextRange()) + ")";
    }
}
