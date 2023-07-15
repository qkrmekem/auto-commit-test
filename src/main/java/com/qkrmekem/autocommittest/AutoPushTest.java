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
import java.net.URLEncoder;


public class AutoPushTest {

    public static void main(String[] args) throws IOException, GitAPIException {

        UserTest userTest = new UserTest();

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
            git.commit().setMessage( "auto-commit" ).call();

            // push to remote:
//            PushCommand pushCommand = git.push();
//            pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(userTest.getUsername().toString(), userTest.getPassword().toString()));
//            OAuth2CredentialsProvider credentialsProvider = new OAuth2CredentialsProvider(accessToken);
            // you can add more settings here if needed
//            pushCommand.call();

            try {
                System.out.println("여기까지 됨");
                git.push()
                        .setCredentialsProvider(new UsernamePasswordCredentialsProvider(userTest.getUsername().toString(), userTest.getPassword().toString()))
                        .setRemote("origin").add("main").call();
            } catch (TransportException e) {
                // Add your own logic here, for example:
//                System.out.println("Username or password incorrect.");
                System.out.println(e.toString());
            }

        }

    }
}
