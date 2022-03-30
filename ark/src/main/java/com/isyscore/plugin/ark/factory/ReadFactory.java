package com.isyscore.plugin.ark.factory;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.isyscore.plugin.ark.Config;
import com.isyscore.plugin.ark.ui.ReadUI;
import org.jetbrains.annotations.NotNull;

public class ReadFactory implements ToolWindowFactory {

    private ReadUI readUI = new ReadUI();

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(readUI.getComponent(), "", false);
        toolWindow.getContentManager().addContent(content);
        Config.readUI = readUI;
    }
}
