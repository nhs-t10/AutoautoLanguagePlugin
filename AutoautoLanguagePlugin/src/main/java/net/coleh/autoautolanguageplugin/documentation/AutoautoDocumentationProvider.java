package net.coleh.autoautolanguageplugin.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.tree.LeafPsiElement;

import net.coleh.autoautolanguageplugin.AutoautoLanguage;
import net.coleh.autoautolanguageplugin.completion.AutoautoLookupElement;
import net.coleh.autoautolanguageplugin.parse.AutoautoLetStatement;
import net.coleh.autoautolanguageplugin.parse.AutoautoVariableReference;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AutoautoDocumentationProvider extends AbstractDocumentationProvider {
    @Override
    public @Nullable String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {

        if(element instanceof BuiltinVariableFlatRecord) return ((BuiltinVariableFlatRecord)element).getDoc();

        String name = getNamed(element, originalElement);

        String doc = JavadocCommentFinder.getFunctionAutocomplete(element.getProject(), name);

        return doc;
    }

    @Nullable
    @Override
    public PsiElement getCustomDocumentationElement(@NotNull Editor editor, @NotNull PsiFile file, @Nullable PsiElement contextElement, int targetOffset) {
        if(contextElement instanceof AutoautoVariableReference) {
            PsiElement target = contextElement.getReference().resolve();
            if(target.getLanguage() == AutoautoLanguage.INSTANCE) return target;
        }
        return null;
    }

    @Override
    public String generateHoverDoc(PsiElement element, PsiElement originalElement) {
        return generateDoc(element, originalElement);
    }


    @Override
    @Nullable
    public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
        return null;
    }

    private static String getNamed(PsiElement element, PsiElement originalElement) {
        String name = null;

        if(element instanceof AutoautoVariableReference) name = ((AutoautoVariableReference) element).getName();
        else if(originalElement instanceof AutoautoVariableReference) name = ((AutoautoVariableReference) originalElement).getName();

        else if(element instanceof AutoautoLetStatement) name = ((AutoautoLetStatement) element).getName();
        else if(originalElement instanceof AutoautoLetStatement) name = ((AutoautoLetStatement) originalElement).getName();

        return name;
    }

    @Override
    public PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object object, PsiElement element) {

        if(object instanceof AutoautoLookupElement) return ((AutoautoLookupElement) object).getPsiElement();

        if(element instanceof LeafPsiElement) return element.getParent();
        else if(element instanceof PsiReference) return ((PsiReference) element).resolve();
        else return element;
    }
}
