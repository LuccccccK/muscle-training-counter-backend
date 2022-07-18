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
aws lambda update-function-code --function-name MtcCfnApi-function --s3-bucket s3-mtc-lambda --s3-key app-1.0-SNAPSHOT.jar
```

## Test

```sh
curl -X GET -I "https://mtc.haba.link/api/health"
curl -X GET -H "Content-Type: application/json" "https://mtc.haba.link/api/result?selectedDate=2022-07-16"
curl -X POST -d @muscle-training-count.json -H "Content-Type: application/json" https://mtc.haba.link/api/result
```

## Others

- CORS対策
  - `https://blog.mori-soft.com/entry/2018/08/24/133304`
