package net.coleh.autoautolanguageplugin.gotoreference;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.model.SymbolResolveResult;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceWrapper;

import net.coleh.autoautolanguageplugin.parse.AutoautoPsiUtilImpl;
import net.coleh.autoautolanguageplugin.parse.AutoautoVariableReference;
import net.coleh.autoautolanguageplugin.parse.AutoautoVisitor;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

//a shim class that helps AutoautoVariableReferences appear to be PsiReferences
public abstract class AutoautoVariableReferenceImplReferenceBridge extends ASTWrapperPsiElement implements PsiReference {
    AutoautoPsiReference reference;

    public AutoautoVariableReferenceImplReferenceBridge(ASTNode n) {
        super(n);
        if(this instanceof AutoautoVariableReference) reference = new AutoautoPsiReference((AutoautoVariableReference) this);
    }
    public PsiNamedElement resolve() {
        if(this instanceof AutoautoVariableReference) reference = new AutoautoPsiReference((AutoautoVariableReference) this);
        return reference.resolve();
    }
    public boolean isReferenceTo(PsiElement e) {
        return reference.isReferenceTo(e);
    }
    public boolean isSoft() {
        return reference.isSoft();
    }
    public PsiElement bindToElement(PsiElement p) {
        return reference.bindToElement(p);
    }
    public PsiElement handleElementRename(String name) {
        return reference.handleElementRename(name);
    }
    public String getCanonicalText() {
        return reference.getCanonicalText();
    }

    @NotNull
    @Override
    public TextRange getRangeInElement() {
        return reference.getRangeInElement();
    }
    public PsiElement getElement() {
        return reference.getElement();
    }

    public Collection<? extends SymbolResolveResult> resolveReference() {
        return new ArrayList<>();
    }

    public String toString() {
        return "wrappedbybridge: " + reference.toString();
    }
}
