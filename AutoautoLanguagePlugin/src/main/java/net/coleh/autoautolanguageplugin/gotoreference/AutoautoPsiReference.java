package net.coleh.autoautolanguageplugin.gotoreference;

import com.intellij.model.SymbolResolveResult;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;

import net.coleh.autoautolanguageplugin.parse.AutoautoLetStatement;
import net.coleh.autoautolanguageplugin.parse.AutoautoVariableReference;
import net.coleh.autoautolanguageplugin.parse.impl.AutoautoVariableReferenceImpl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public class AutoautoPsiReference implements PsiReference {
    private final AutoautoVariableReference node;
    public String filename;
    String name;
    public AutoautoPsiReference(AutoautoVariableReference node) {
        this.node = node;
        this.name = node.getName();
        this.filename = node.getContainingFile().getName();
    }

    @NotNull
    @Override
    public PsiElement getElement() {
        return node;
    }

    @NotNull
    @Override
    //TODO: make this work. It's an experimental API, so it doesn't need to work for now (December 2021)
    public Collection<? extends SymbolResolveResult> resolveReference() {
        return new ArrayList<>();
    }

    @NotNull
    @Override
    public TextRange getRangeInElement() {
        return node.getTextRange();
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        AutoautoLetStatement[] letStatements = PsiTreeUtil.getChildrenOfType(node.getContainingFile(), AutoautoLetStatement.class);
        if(letStatements != null) {
            for (AutoautoLetStatement s : letStatements) {
                if (s.getName().equals(name)) return s;
            }
        }
        //no items-- therefore, must be built-in (or null)
        return JavaMethodFinder.getByName(node.getProject(), name);
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
        return true;
    }

    @Override
    public String toString() {
        return "ref:" + name;
    }
}
