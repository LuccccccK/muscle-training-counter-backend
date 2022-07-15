# README

## フォルダ構成

- 下記サイトを参考
  - `https://qiita.com/Yuuki557/items/b9e456f9841f3335d574`

## Deploy

```sh
mvn clean
mvn package
aws s3 cp target/app-1.0-SNAPSHOT.jar s3://s3-mtc-lambda/
# 管理コンソール上でLambda関数を更新
```

## Test

```sh
curl -X GET -H "Content-Type: application/json" https://mtc.haba.link/api/result
curl -X POST -d @muscle-training-count.json -H "Content-Type: application/json" https://mtc.haba.link/api/result
```
