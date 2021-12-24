package net.coleh.autoautolanguageplugin.parse;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiWhiteSpace;

import net.coleh.autoautolanguageplugin.NotificationShowerHelper;
import net.coleh.autoautolanguageplugin.gotoreference.AutoautoPsiReference;
import net.coleh.autoautolanguageplugin.gotoreference.PsiMakerHelper;
import net.coleh.autoautolanguageplugin.parse.impl.AutoautoVariableReferenceImpl;

import java.util.List;

public class AutoautoPsiUtilImpl {

    public enum BaseExpressionType {
        FUNCTION_CALL,
        ARRAY_GET,
        OBJECT_GET,
        VARIABLE
    }

    public static BaseExpressionType getBaseExpressionType(AutoautoVariableReference atom) {
        PsiElement parent = atom.getParent();

        //if this is the `e` in `e.foobar.bazboo()` or `e` or `e()`
        if(parent instanceof AutoautoAtom) {
            AutoautoBaseExpression baseExpression = (AutoautoBaseExpression)parent.getParent();
            List<AutoautoTail> tails = baseExpression.getTailList();
            if(tails.size() == 0) return BaseExpressionType.VARIABLE;
            else return getBaseExpressionTypeFromTail(tails.get(0));
        }
        //if this is the `a` in `foobaz.a.fef()` or `boof.a()`
        else if(parent instanceof AutoautoPropertyGetTail) {
            return getBaseExpressionTypeFromTail(
                (AutoautoTail) (parent
                    .getParent() //an AutoautoSettableTail
                    .getParent() //an AutoautoTail
                    .getNextSibling()) //either another AutoautoTail or null
            );
        }
        return BaseExpressionType.VARIABLE;
    }

    private static BaseExpressionType getBaseExpressionTypeFromTail(AutoautoTail t) {
        if(t == null) return BaseExpressionType.VARIABLE;

        if(t.getFunctionCallTail() != null) return BaseExpressionType.FUNCTION_CALL;

        if(t.getSettableTail() == null) throw new IllegalArgumentException("Somehow a bad AST got made");
        if(t.getSettableTail().getArrayElementGetTail() != null) return BaseExpressionType.ARRAY_GET;
        if(t.getSettableTail().getPropertyGetTail() != null) return BaseExpressionType.OBJECT_GET;

        return BaseExpressionType.VARIABLE;
    }
    public static String getCannonicalName(PsiElement v) {
        PsiElement p = v;
        //search 7 iterations up for an AutoautoVariableReference
        for(int i = 0; i < 7 && !(p instanceof AutoautoVariableReference) && p != null; i++) p = p.getParent();

        if(p instanceof AutoautoVariableReference) return getCannonicalName((AutoautoVariableReference)p);
        else return "";
    }
    public static String getCannonicalName(AutoautoVariableReference var) {
        if(var == null) return null;

        //if it's just `foo`, return it directly!
        if(var.getParent() instanceof AutoautoAtom) return var.getText();

        AutoautoTail selfTail = (AutoautoTail)(
            var //VariableReference
            .getParent() //PropertyGetTail
            .getParent() //SettableTail
            .getParent() //Tail
        );

        //get the base expression! There should always be a base expression somewhere in a VariableReference's tree.
        AutoautoBaseExpression baseExpression = (AutoautoBaseExpression) selfTail.getParent();

        StringBuilder name = new StringBuilder(getPrototypeLiteralName(baseExpression.getAtom()));

        //get the tail's index
        List<AutoautoTail> tails = baseExpression.getTailList();
        int indexOfThisTail = baseExpression.getTailList().indexOf(selfTail);

        for(int i = 0; i <= indexOfThisTail; i++) {
            AutoautoTail t = tails.get(i);
            if(t.getFunctionCallTail() != null) {
                name.append("()");
                continue;
            }
            AutoautoSettableTail settable = t.getSettableTail();
            if(settable.getArrayElementGetTail() != null) name.append("[]");
            else name.append(settable.getPropertyGetTail().getText());
        }

        return name.toString();
    }
    public static String getPrototypeLiteralName(AutoautoAtom atom) {
        if(atom.getArrayLiteral() != null) return "Array";
        if(atom.getBooleanLiteral() != null) return "Boolean";
        if(atom.getFunctionExpression() != null) return "Function";
        if(atom.getUnitValue() != null) return "UnitValue";
        if(atom.getNumber() != null) return "Number";
        if(atom.getValueInParens() != null) return "<anon>";
        if(atom.getVariableReference() != null) return atom.getVariableReference().getText();

        return "";
    }
    public static String getLabel(AutoautoLabeledStatepath element) {
        ASTNode keyNode = element.getNode().findChildByType(AutoautoTypes.STATEPATH_LABEL_ID);
        if (keyNode != null) {
            String label = keyNode.getText();
            if(label.charAt(0) == '#') label = label.substring(1);
            return label.trim();
        } else {
            return null;
        }
    }

    public static PsiElement setName(AutoautoVariableReference statement, String name) {
        //have to do this bc `getChildren()` doesn't include leafs ://
        statement.getFirstChild().replace(PsiMakerHelper.makeIdentifier(statement.getProject(), name));
        return statement;
    }

    public static PsiElement setName(AutoautoLetStatement statement, String name) {
        //have to do this bc `getChildren()` doesn't include leafs ://
        statement.getNameIdentifier().replace(PsiMakerHelper.makeIdentifier(statement.getProject(), name));
        return statement;
    }

    public static PsiElement setName(AutoautoFuncDefStatement statement, String name) {
        //have to do this bc `getChildren()` doesn't include leafs ://
        statement.getNameIdentifier().replace(PsiMakerHelper.makeIdentifier(statement.getProject(), name));
        return statement;
    }

    public static String getName(AutoautoLetStatement statement) {
        //have to do this bc `getChildren()` doesn't include leafs ://
        return statement.getNameIdentifier().getText();
    }

    public static String getName(AutoautoFuncDefStatement statement) {
        //have to do this bc `getChildren()` doesn't include leafs ://
        return statement.getNameIdentifier().getText();
    }

    public static String getName(AutoautoVariableReference statement) {
        //return statement.getFirstChild().getText();
        return getCannonicalName(statement);
    }

    public static int getTextOffset(AutoautoLetStatement node) {
        return node.getNameIdentifier().getStartOffsetInParent();
    }

    public static int getTextOffset(AutoautoFuncDefStatement node) {
        return node.getNameIdentifier().getStartOffsetInParent();
    }

    public static PsiReference[] getReferences(AutoautoVariableReferenceImpl node) {
        return new PsiReference[] { getReference(node) };
    }
    public static PsiElement resolve(AutoautoVariableReference node) {
        return (new AutoautoPsiReference(node)).resolve();
    }
    public static PsiReference getReference(AutoautoVariableReferenceImpl node) {
        return node;
    }
    public static PsiElement getNameIdentifier(AutoautoLetStatement letStatement) {
        //have to do this bc `getChildren()` doesn't include leafs ://
        PsiElement p = letStatement.getFirstChild().getNextSibling();
        while(p instanceof PsiWhiteSpace) p = p.getNextSibling();
        return p;
    }
    public static PsiElement getNameIdentifier(AutoautoFuncDefStatement letStatement) {
        //have to do this bc `getChildren()` doesn't include leafs ://
        PsiElement p = letStatement.getFirstChild().getNextSibling();
        while(p instanceof PsiWhiteSpace) p = p.getNextSibling();
        return p;
    }
}
