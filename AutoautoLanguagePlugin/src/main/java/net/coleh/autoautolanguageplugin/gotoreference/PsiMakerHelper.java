package net.coleh.autoautolanguageplugin.gotoreference;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.search.PsiSearchHelper;
import com.intellij.psi.util.PsiTreeUtil;

import net.coleh.autoautolanguageplugin.AutoautoFileType;
import net.coleh.autoautolanguageplugin.parse.AutoautoFile;
import net.coleh.autoautolanguageplugin.parse.AutoautoLabeledStatepath;
import net.coleh.autoautolanguageplugin.parse.AutoautoVariableReference;

import java.util.prefs.PreferenceChangeEvent;

public class PsiMakerHelper {
    public static PsiElement makeIdentifier(Project project, String name) {
        String source = "#i: " + name;
        final AutoautoFile file = createFile(project, source);
        return PsiTreeUtil.getChildOfType(file, AutoautoVariableReference.class).getFirstChild();
    }
    public static PsiElement makeStatepathLabel(Project project, String name) {
        String source = name + ": " + "3";
        final AutoautoFile file = createFile(project, source);
        return PsiTreeUtil.getChildOfType(file, AutoautoLabeledStatepath.class).getFirstChild();
    }

    public static PsiElement makeStatepath(Project project, String name) {
        String source = name + ":\n    pass";
        AutoautoFile f = createFile(project, source);
        return PsiTreeUtil.getChildOfType(f, AutoautoLabeledStatepath.class);
    }
    public static PsiElement makeNewline(Project project) {
        String source = "\n";
        AutoautoFile f = createFile(project, source);
        return f.getFirstChild();
    }

    public static AutoautoFile createFile(Project project, String text) {
        String name = "dummy.autoauto";
        return (AutoautoFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, AutoautoFileType.INSTANCE, text);
    }
}
