package net.coleh.autoautolanguageplugin.documentation;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.lang.documentation.DocumentationProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiTreeUtil;

import net.coleh.autoautolanguageplugin.parse.AutoautoLetStatement;
import net.coleh.autoautolanguageplugin.parse.AutoautoTokenType;
import net.coleh.autoautolanguageplugin.parse.AutoautoTypes;
import net.coleh.autoautolanguageplugin.parse.AutoautoVariableReference;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class AutoautoDocumentationProvider extends AbstractDocumentationProvider {
    @Override
    public @Nullable String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
        String name = null;

        if(element instanceof AutoautoVariableReference) name = ((AutoautoVariableReference) element).getName();
        else if(originalElement instanceof AutoautoVariableReference) name = ((AutoautoVariableReference) originalElement).getName();

        else if(element instanceof AutoautoLetStatement) name = ((AutoautoLetStatement) element).getName();
        else if(originalElement instanceof AutoautoLetStatement) name = ((AutoautoLetStatement) originalElement).getName();


        String doc = JavadocCommentFinder.getFunctionAutocomplete(element.getProject(), name);

        if(doc == null) throw new IllegalStateException(element.getText() + " -- " + "--" + name);

        return doc;
    }

    @Override
    public String generateHoverDoc(PsiElement element, PsiElement originalElement) {
        return generateDoc(element, originalElement);
    }


    //TODO: implement
    @Override
    public PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object object, PsiElement element) {
        return null;
    }
}
