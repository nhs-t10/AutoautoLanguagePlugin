// This is a generated file. Not intended for manual editing.
package net.coleh.autoautolanguageplugin.parse;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import net.coleh.autoautolanguageplugin.parse.AutoautoPsiUtilImpl.BaseExpressionType;

public interface AutoautoVariableReference extends PsiElement {

  BaseExpressionType getBaseExpressionType();

  PsiReference getReference();

  String getName();

  PsiElement setName(String name);

  PsiReference[] getReferences();

}
