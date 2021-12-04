// This is a generated file. Not intended for manual editing.
package net.coleh.autoautolanguageplugin.parse.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.coleh.autoautolanguageplugin.parse.AutoautoTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import net.coleh.autoautolanguageplugin.parse.*;

public class AutoautoLetStatementImpl extends ASTWrapperPsiElement implements AutoautoLetStatement {

  public AutoautoLetStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull AutoautoVisitor visitor) {
    visitor.visitLetStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof AutoautoVisitor) accept((AutoautoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<AutoautoCommentOpportunity> getCommentOpportunityList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, AutoautoCommentOpportunity.class);
  }

  @Override
  @NotNull
  public List<AutoautoSettableTail> getSettableTailList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, AutoautoSettableTail.class);
  }

  @Override
  @NotNull
  public AutoautoValue getValue() {
    return findNotNullChildByClass(AutoautoValue.class);
  }

  @Override
  public String getName() {
    return AutoautoPsiUtilImpl.getName(this);
  }

  @Override
  public PsiElement setName(String name) {
    return AutoautoPsiUtilImpl.setName(this, name);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return AutoautoPsiUtilImpl.getNameIdentifier(this);
  }

}
