package net.coleh.autoautolanguageplugin.findusages;

import com.intellij.codeInsight.hint.HintManager;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;

import net.coleh.autoautolanguageplugin.NotificationShowerHelper;
import net.coleh.autoautolanguageplugin.parse.AutoautoLetStatement;
import net.coleh.autoautolanguageplugin.parse.AutoautoTypes;
import net.coleh.autoautolanguageplugin.parse.AutoautoVariableReference;
import net.coleh.autoautolanguageplugin.parse.lexer.AutoautoLexerAdapter;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AutoautoFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(new AutoautoLexerAdapter(),
                TokenSet.create(AutoautoTypes.IDENTIFIER),
                TokenSet.create(AutoautoTypes.COMMENT_TEXT, AutoautoTypes.COMMENT_BEGIN,
                        AutoautoTypes.COMMENT_END, AutoautoTypes.LINE_COMMENT_BEGIN),
                TokenSet.create(AutoautoTypes.STRING_LITERAL, AutoautoTypes.NUMERIC_VALUE,
                        AutoautoTypes.NUMERIC_VALUE_WITH_UNIT,AutoautoTypes.BOOLEAN_LITERAL,
                        AutoautoTypes.UNIT_VALUE));
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        if(psiElement instanceof PsiNamedElement) return true;
        else {
            return false;
        }
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @Nls
    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        return getDescriptiveName(element);
    }

    @Nls
    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        return element.getText();
    }

    @Nls
    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        return null;
    }
}
