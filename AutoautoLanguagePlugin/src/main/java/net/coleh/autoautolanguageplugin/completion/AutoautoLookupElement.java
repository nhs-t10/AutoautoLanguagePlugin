package net.coleh.autoautolanguageplugin.completion;

import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementPresentation;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.EditorModificationUtil;
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
    private String definingManager;

    @NotNull
    @Override
    public String getLookupString() {
        return name;
    }

    public AutoautoLookupElement(String name, String[] args, String returnType, Icon icon, String definingManager) {
        this.name = name;
        this.args = args;
        this.returnType = returnType;
        this.icon = icon;
        this.definingManager = definingManager;
    }

    public AutoautoLookupElement(String name, Icon icon) {
        this.name = name;
        this.icon = icon;
    }

    @Override
    public void handleInsert(InsertionContext context) {
        if(this.args != null) {
            int tailOffset = context.getTailOffset();
            Document doc = context.getEditor().getDocument();

            doc.insertString(tailOffset, "()");

            context.getEditor().getCaretModel().moveToOffset(tailOffset + 1);

            //the editormodificationutil is confusing so i'll just use lower-level methods for now
            //EditorModificationUtil.insertStringAtCaret(context.getEditor(), "(", -1);
        }
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

        if(definingManager != null) presentation.setTypeText(definingManager);
    }

    public boolean isWorthShowingInAutoPopup() {
        return true;
    }
}
