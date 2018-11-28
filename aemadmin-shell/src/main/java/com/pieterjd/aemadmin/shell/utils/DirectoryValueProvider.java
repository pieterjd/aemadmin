package com.pieterjd.aemadmin.shell.utils;

import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.FileValueProvider;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryValueProvider extends FileValueProvider {
    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext, String[] hints) {
        return super.complete(parameter, completionContext, hints)
                .stream()
                .map(proposal -> new File(proposal.value()))
                .filter(file -> file.isDirectory())
                .map(file -> new CompletionProposal(file.toString()))
                .collect(Collectors.toList());
    }
}
