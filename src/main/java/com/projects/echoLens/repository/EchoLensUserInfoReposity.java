package com.projects.echoLens.repository;

import com.projects.echoLens.EchoLensConstants;
import com.projects.echoLens.entity.EchoUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.inject.Named;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.google.gson.Gson;

@Repository
public class EchoLensUserInfoReposity {
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoLensUserInfoReposity.class);

    private final MongoCollection<Document> echoUserInfo;

    @Inject
    public EchoLensUserInfoReposity(@Named("echoLens.userInfo") final MongoCollection<Document> echoUserInfo) {
        this.echoUserInfo = echoUserInfo;
    }

    public void insert(final EchoUserInfo echoUserInfoEntity, final String useremail) throws Exception {
        Document doc = Document.parse(new Gson().toJson(echoUserInfoEntity));
        try {
            Document filter = new Document(EchoLensConstants.ECHO_USER_EMAIL, new Document("$eq", useremail));
            if (echoUserInfo.countDocuments(filter) > 0) {
                echoUserInfo.updateOne(filter, new org.bson.Document("$set", doc));
                LOGGER.info("Updated into echoUserInfo");
            }
            else {
                echoUserInfo.insertOne(doc);
                LOGGER.info("Inserted into echoUserInfo");
            }
        }
        catch (Exception e) {
            LOGGER.error("Exception occurred while inserting user info into database", e);
        }

    }
}
