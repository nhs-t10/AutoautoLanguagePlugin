// This is a generated file. Not intended for manual editing.
package net.coleh.autoautolanguageplugin.parse;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface AutoautoAtom extends PsiElement {

  @Nullable
  AutoautoArrayLiteral getArrayLiteral();

  @Nullable
  AutoautoBooleanLiteral getBooleanLiteral();

  @NotNull
  List<AutoautoCommentOpportunity> getCommentOpportunityList();

  @Nullable
  AutoautoFunctionExpression getFunctionExpression();

  @Nullable
  AutoautoNumber getNumber();

  @Nullable
  AutoautoStringLiteral getStringLiteral();

  @Nullable
  AutoautoUnitValue getUnitValue();

  @Nullable
  AutoautoValueInParens getValueInParens();

  @Nullable
  AutoautoVariableReference getVariableReference();

}
