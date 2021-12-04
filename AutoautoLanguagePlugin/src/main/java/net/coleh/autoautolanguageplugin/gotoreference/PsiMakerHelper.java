package net.coleh.autoautolanguageplugin.gotoreference;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.search.PsiSearchHelper;
import com.intellij.psi.util.PsiTreeUtil;

import net.coleh.autoautolanguageplugin.AutoautoFileType;
import net.coleh.autoautolanguageplugin.parse.AutoautoFile;
import net.coleh.autoautolanguageplugin.parse.AutoautoVariableReference;

public class PsiMakerHelper {
    public static PsiElement makeIdentifier(Project project, String name) {
        String source = "#i: " + name;
        final AutoautoFile file = createFile(project, source);
        return PsiTreeUtil.getChildOfType(file, AutoautoVariableReference.class).getFirstChild();
    }

    public static AutoautoFile createFile(Project project, String text) {
        String name = "dummy.autoauto";
        return (AutoautoFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, AutoautoFileType.INSTANCE, text);
    }
}
