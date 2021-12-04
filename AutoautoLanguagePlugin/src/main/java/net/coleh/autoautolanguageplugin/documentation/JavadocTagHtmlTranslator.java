package net.coleh.autoautolanguageplugin.documentation;

import com.intellij.psi.PsiElement;
import com.intellij.psi.javadoc.PsiDocTag;

public class JavadocTagHtmlTranslator {
    public static String getHtml(PsiDocTag tag) {
        switch(tag.getName()) {
            case "code": return codeTagToHtml(tag);
            case "inheritDoc": return "";
            case "linkplain":
                case "link": return linkTagToHtml(tag);
            case "literal": return literalTagToHtml(tag);
            case "value":
            default: return "<code>" + tag.getText() + "</code>";
        }
    }
    public static String getHtml(PsiElement e) {
        if(e instanceof PsiDocTag) return getHtml((PsiDocTag)e);
        else return htmlEscape(e.getText());
    }


    private static String htmlEscape(String str) {
        return str
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("&", "&amp;");
    }

    private static String literalTagToHtml(PsiDocTag tag) {
        return getHtml(tag.getDataElements()[0]);
    }

    private static String linkTagToHtml(PsiDocTag tag) {
        PsiElement[] dataElements = tag.getDataElements();
        return "<a href=\"" + getHtml(dataElements[1]) + "\">" + getHtml(dataElements.length > 2 ? dataElements[2] : dataElements[1]) + "</a>";
    }

    private static String codeTagToHtml(PsiDocTag tag) {
        return "<code>" + getHtml(tag.getDataElements()[0]) + "</code>";
    }
}
