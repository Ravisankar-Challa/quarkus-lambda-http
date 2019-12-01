#!/bin/bash
#./mvnw package
cp target/*-runner target/bootstrap
docker --tlsverify=false run --rm -v $PWD/target:/var/task -i lambci/lambda:provided handler '{"body":"{\"name\":\"423\"}","resource":"/{proxy+}","path":"/api/get/validate/86","httpMethod":"GET","isBase64Encoded":false,"queryStringParameters":null,"headers": {"Content-Type":"application/json"}, "multiValueHeaders":{"Accept-Encoding":["gzip","deflate"], "Content-Type":["application/json"]},"multiValueQueryStringParameters":{"test":["111"], "names":["dog","fish"]},"pathParameters":{"proxy":"hello"},"headers":null,"requestContext":{"path":"/{proxy+}","resourcePath":"/{proxy+}","httpMethod":"GET","protocol":"HTTP\/1.1","apiId":"1234567890","accountId":"123456789012","resourceId":"123456","stage":"prod","requestId":"c6af9ac6-7b61-11e6-9a41-93e8deadbeef","identity":{"cognitoIdentityPoolId":null,"accountId":null,"cognitoIdentityId":null,"caller":null,"accessKey":null,"sourceIp":"127.0.0.1","cognitoAuthenticationType":null,"cognitoAuthenticationProvider":null,"userArn":null,"userAgent":"Custom User Agent String","user":null}}}'