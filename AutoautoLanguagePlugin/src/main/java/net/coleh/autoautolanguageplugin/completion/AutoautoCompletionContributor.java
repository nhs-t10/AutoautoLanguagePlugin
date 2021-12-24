package net.coleh.autoautolanguageplugin.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;


import net.coleh.autoautolanguageplugin.AutoautoIcons;
import net.coleh.autoautolanguageplugin.documentation.BuiltinVariableFlatRecord;
import net.coleh.autoautolanguageplugin.parse.AutoautoPsiUtilImpl;
import net.coleh.autoautolanguageplugin.parse.AutoautoTypes;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AutoautoCompletionContributor extends CompletionContributor {
    public AutoautoCompletionContributor() {}

    @Override
    public void fillCompletionVariants(CompletionParameters parameters, @NotNull CompletionResultSet resultSet) {
        if(!parameters.getCompletionType().equals(CompletionType.BASIC)) return;
        if(parameters.getEditor().getProject() == null) return;

        //don't try to complete in quotes
        PsiElement prevSibling = parameters.getPosition().getPrevSibling();
        if(prevSibling instanceof LeafPsiElement) {
            if(((LeafPsiElement)prevSibling).getElementType().equals(AutoautoTypes.NON_QUOTE_CHARACTER)) {
                return;
            }
        }

        //if the person is typing the `foo` in `bar.baz.foo`, this should be `bar.baz`
        String context = AutoautoPsiUtilImpl.getCannonicalName(parameters.getPosition());
        if(context == null) context = "";
        context = context.substring(0, Math.max(0, context.lastIndexOf('.')));

        if(context.equals("")) {
            for (AutoautoLookupElement record : ManagerMethodLister.getFunctionAutocompleteList(parameters.getEditor().getProject())) {
                resultSet.addElement(record);
            }

            //add local definitions, like statepath names, variables, and such
            ArrayList<AutoautoLookupElement> inFile = new ArrayList<>();
            FileDefinedWordsLister.addDefinedWords(parameters.getOriginalFile(), inFile);
            for(AutoautoLookupElement record : inFile) resultSet.addElement(record);
        }

        BuiltinVariableFlatRecord.addBuiltIns(context, resultSet, parameters.getEditor().getProject());

        resultSet.addElement(new AutoautoLookupElement("james", AutoautoIcons.JAMES,null));
    }
}
