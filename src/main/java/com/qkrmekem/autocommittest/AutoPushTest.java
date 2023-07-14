package com.qkrmekem.autocommittest;

import com.qkrmekem.autocommittest.component.UserTest;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;



public class AutoPushTest {

    public static void main(String[] args) throws IOException, GitAPIException {

        UserTest userTest = new UserTest();
        System.out.println("userTest.getUsername() = " + userTest.getUsername());
        System.out.println("userTest.getPassword() = " + userTest.getPassword());

        try (Git git = Git.open(new File("D:\\auto-commit-test"))) {
            // Add your logic here ...
            RemoteAddCommand remoteAddCommand = git.remoteAdd();
            remoteAddCommand.setName("origin");
            try {
                remoteAddCommand.setUri(new
                        URIish("https://github.com/qkrmekem/auto-commit-test.git"));
                System.out.println("file Added");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            git.add().addFilepattern(".").call();
            // you can add more settings here if needed
            remoteAddCommand.call();
            git.commit().setMessage( "commited" ).call();

            // push to remote:
            PushCommand pushCommand = git.push();
            pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(userTest.getUsername(), userTest.getPassword()));
            // you can add more settings here if needed
            pushCommand.call();

            try {
                git.push().setRemote("origin").add("master").call();
            } catch (TransportException e) {
                // Add your own logic here, for example:
                System.out.println("Username or password incorrect.");
            }

        }

    }
}
