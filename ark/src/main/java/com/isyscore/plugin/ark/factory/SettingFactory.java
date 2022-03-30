package com.isyscore.plugin.ark.factory;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.ui.Messages;
import com.isyscore.plugin.ark.Config;
import com.isyscore.plugin.ark.ui.SettingUI;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class SettingFactory implements SearchableConfigurable {
    private SettingUI settingUI = new SettingUI();

    @NotNull
    @Override
    public String getId() {
        return "test.id";
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "test-config";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return settingUI.getComponent();
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        String url = settingUI.getUrlTextField().getText();

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(new File(url),"r")) {
            randomAccessFile.seek(0);
            byte[] bytes = new byte[1024 * 1024];
            int readSize = randomAccessFile.read(bytes);
            byte[] copy = new byte[readSize];
            System.arraycopy(bytes,0,copy,0,readSize);

            String str = new String(copy, StandardCharsets.UTF_8);
            if (Config.readUI == null) {
                Messages.showMessageDialog("new instance","i am title",Messages.getInformationIcon());
            } else {
                Config.readUI.getTextContent().setText(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
