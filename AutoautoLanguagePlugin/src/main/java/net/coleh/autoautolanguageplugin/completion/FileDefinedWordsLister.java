package net.coleh.autoautolanguageplugin.completion;

import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;

import net.coleh.autoautolanguageplugin.AutoautoIcons;
import net.coleh.autoautolanguageplugin.parse.AutoautoArgument;
import net.coleh.autoautolanguageplugin.parse.AutoautoArgumentList;
import net.coleh.autoautolanguageplugin.parse.AutoautoFuncDefStatement;
import net.coleh.autoautolanguageplugin.parse.AutoautoLabeledStatepath;
import net.coleh.autoautolanguageplugin.parse.AutoautoLetStatement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileDefinedWordsLister {
    public static void addDefinedWords(PsiFile file, ArrayList<AutoautoLookupElement> autocompleteElements) {
        Collection<AutoautoLetStatement> letStatements = PsiTreeUtil.findChildrenOfType(file, AutoautoLetStatement.class);
        for(AutoautoLetStatement l : letStatements) {
            autocompleteElements.add(new AutoautoLookupElement(l.getName(), AutoautoIcons.LOCAL_VARIABLE, l));
        }

        Collection<AutoautoFuncDefStatement> funcDefStatements = PsiTreeUtil.findChildrenOfType(file, AutoautoFuncDefStatement.class);
        for(AutoautoFuncDefStatement f : funcDefStatements) {
            autocompleteElements.add(new AutoautoLookupElement(f.getName(), formatLocalFuncArgs(f),
                    "any", AutoautoIcons.LOCAL_VARIABLE, null, f));
        }

        Collection<AutoautoLabeledStatepath> statepaths = PsiTreeUtil.findChildrenOfType(file,AutoautoLabeledStatepath.class);
        for(AutoautoLabeledStatepath s : statepaths) {
            autocompleteElements.add(new AutoautoLookupElement(s.getLabel(), AutoautoIcons.STATEPATH, s));
        }
    }

    private static String[] formatLocalFuncArgs(AutoautoFuncDefStatement f) {
        AutoautoArgumentList argList = f.getFunctionArgsBody().getArgumentList();

        if(argList == null) return new String[0];

        List<AutoautoArgument> args = argList.getArgumentList();

        String[] result = new String[args.size()];

        for(int i = 0; i < result.length; i++) {
            AutoautoArgument currentArg = args.get(i);
            result[i] = currentArg.getText() + ": any";
        }
        return result;
    }
}
