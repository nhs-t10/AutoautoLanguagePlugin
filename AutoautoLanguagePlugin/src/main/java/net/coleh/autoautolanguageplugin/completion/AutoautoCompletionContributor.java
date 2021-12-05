package net.coleh.autoautolanguageplugin.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.LookupElementBuilder;


import net.coleh.autoautolanguageplugin.AutoautoIcons;
import net.coleh.autoautolanguageplugin.NotificationShowerHelper;
import net.coleh.autoautolanguageplugin.documentation.JavadocCommentFinder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AutoautoCompletionContributor extends CompletionContributor {
    public AutoautoCompletionContributor() {}

    @Override
    public void fillCompletionVariants(CompletionParameters parameters, @NotNull CompletionResultSet resultSet) {
        if(!parameters.getCompletionType().equals(CompletionType.BASIC)) return;
        if(parameters.getEditor().getProject() == null) return;

        for(AutoautoLookupElement record : ManagerMethodLister.getFunctionAutocompleteList(parameters.getEditor().getProject())) {
            resultSet.addElement(record);
        }

        //add local definitions, like statepath names, variables, and such
        ArrayList<AutoautoLookupElement> inFile = new ArrayList<>();
        FileDefinedWordsLister.addDefinedWords(parameters.getOriginalFile(), inFile);
        for(AutoautoLookupElement record : inFile) resultSet.addElement(record);

        resultSet.addElement(new AutoautoLookupElement("James", AutoautoIcons.JAMES,null));
    }
}
