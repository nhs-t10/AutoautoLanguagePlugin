// This is a generated file. Not intended for manual editing.
package net.coleh.autoautolanguageplugin.parse;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface AutoautoFuncDefStatement extends PsiNameIdentifierOwner {

  @NotNull
  AutoautoFunctionArgsBody getFunctionArgsBody();

  String getName();

  PsiElement setName(String name);

  PsiElement getNameIdentifier();

  int getTextOffset();

}
