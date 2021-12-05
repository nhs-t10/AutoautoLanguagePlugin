package net.coleh.autoautolanguageplugin.documentation;

import com.intellij.codeInsight.documentation.DocumentationManager;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.LanguageDocumentation;
import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.lang.documentation.DocumentationMarkup;
import com.intellij.lang.documentation.DocumentationProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.util.PsiTreeUtil;

import net.coleh.autoautolanguageplugin.AutoautoLanguage;
import net.coleh.autoautolanguageplugin.NotificationShowerHelper;
import net.coleh.autoautolanguageplugin.completion.AutoautoLookupElement;
import net.coleh.autoautolanguageplugin.parse.AutoautoLetStatement;
import net.coleh.autoautolanguageplugin.parse.AutoautoTokenType;
import net.coleh.autoautolanguageplugin.parse.AutoautoTypes;
import net.coleh.autoautolanguageplugin.parse.AutoautoVariableReference;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class AutoautoDocumentationProvider extends AbstractDocumentationProvider {
    @Override
    public @Nullable String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
        String name = getNamed(element, originalElement);

        String doc = JavadocCommentFinder.getFunctionAutocomplete(element.getProject(), name);

        return doc;
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
        NotificationShowerHelper.showNotif("DEBUG", "getDocumentationElementForLookupItem called",
                "egc: " + element.getClass().toString() + "\n" +
                        "egt: " + element.getText() + "\n" +
                        "ogc: " + object.getClass().toString(),
                element.getProject());

        if(object instanceof AutoautoLookupElement) return ((AutoautoLookupElement) object).getPsiElement();

        if(element instanceof LeafPsiElement) return element.getParent();
        else return element;
    }
}
