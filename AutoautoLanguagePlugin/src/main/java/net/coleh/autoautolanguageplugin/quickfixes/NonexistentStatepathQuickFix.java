package net.coleh.autoautolanguageplugin.quickfixes;

import com.android.tools.idea.naveditor.property.inspector.SimpleProperty;
import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;

import net.coleh.autoautolanguageplugin.gotoreference.PsiMakerHelper;
import net.coleh.autoautolanguageplugin.parse.AutoautoFile;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

import static com.intellij.openapi.command.WriteCommandAction.*;

public class NonexistentStatepathQuickFix extends BaseIntentionAction {

    private final String name;

    public NonexistentStatepathQuickFix(String name) {
        this.name = name;
    }

    @NotNull
    @Override
    public String getText() {
        return "Create statepath '" + name + "'";
    }

    @NotNull
    @Override
    public String getFamilyName() {
        return "Create statepath";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return true;
    }
    @Override
    public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws IncorrectOperationException {
        createStatepath(project, file);
    }

    private void createStatepath(Project project, PsiFile file) {
        writeCommandAction(project).run(() -> {
            AutoautoFile aaFile = (AutoautoFile) file;
            aaFile.getNode().addChild(PsiMakerHelper.makeNewline(project).getNode());
            aaFile.getNode().addChild(PsiMakerHelper.makeNewline(project).getNode());
            aaFile.getNode().addChild(PsiMakerHelper.makeStatepath(project, "#" + name).getNode());
        });
    }
}
