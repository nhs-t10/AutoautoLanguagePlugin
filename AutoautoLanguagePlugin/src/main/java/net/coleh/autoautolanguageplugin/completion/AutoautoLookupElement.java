package net.coleh.autoautolanguageplugin.completion;

import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

import net.coleh.autoautolanguageplugin.AutoautoIcons;

import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;

public class AutoautoLookupElement extends LookupElement {
    private String returnType;
    private String name;
    private String[] args;
    private Icon icon;

    @NotNull
    @Override
    public String getLookupString() {
        return name;
    }

    public AutoautoLookupElement(String name, String[] args, String returnType, Icon icon) {
        this.name = name;
        this.args = args;
        this.returnType = returnType;
        this.icon = icon;
    }

    public AutoautoLookupElement(String name, Icon icon) {
        this.name = name;
        this.icon = icon;
    }

    @Override
    public void handleInsert(InsertionContext context) {
        PsiFile file = context.getFile();
        PsiElement elem = file.findElementAt(context.getSelectionEndOffset());

    }
    @Override
    public void renderElement(LookupElementPresentation presentation) {
        presentation.setIcon(this.icon);
        presentation.setItemText(name);

        if(this.args != null) {
            String argsJoined = String.join(", ", this.args);
            presentation.appendTailText("(" + argsJoined + ")", true);
        }

        if(returnType != null && returnType.length() > 0) presentation.appendTailTextItalic(" : " + returnType, true);
    }

    public boolean isWorthShowingInAutoPopup() {
        return true;
    }
}
