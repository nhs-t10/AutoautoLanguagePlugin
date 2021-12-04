package net.coleh.autoautolanguageplugin.completion;

import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;

import net.coleh.autoautolanguageplugin.AutoautoIcons;
import net.coleh.autoautolanguageplugin.parse.AutoautoLabeledStatepath;
import net.coleh.autoautolanguageplugin.parse.AutoautoLetStatement;

import java.util.ArrayList;
import java.util.Collection;

public class FileDefinedWordsLister {
    public static void addDefinedWords(PsiFile file, ArrayList<AutoautoLookupElement> autocompleteElements) {
        Collection<AutoautoLetStatement> letStatements = PsiTreeUtil.findChildrenOfType(file, AutoautoLetStatement.class);
        for(AutoautoLetStatement l : letStatements) {
            autocompleteElements.add(new AutoautoLookupElement(l.getName(), AutoautoIcons.LOCAL_VARIABLE));
        }

        Collection<AutoautoLabeledStatepath> statepaths = PsiTreeUtil.findChildrenOfType(file,AutoautoLabeledStatepath.class);
        for(AutoautoLabeledStatepath s : statepaths) {
            autocompleteElements.add(new AutoautoLookupElement(s.getLabel(), AutoautoIcons.STATEPATH));
        }
    }
}
