# README

1. DB構築

    ```sh
    aws cloudformation create-stack --stack-name MtcCfnDatabase --template-body file://mtc-cfn-database.yaml
    # aws cloudformation update-stack --stack-name MtcCfnDatabase --template-body file://mtc-cfn-database.yaml
    # aws cloudformation delete-stack --stack-name MtcCfnDatabase
    ```

1. SNSトピックを作成

    ```sh
    # Pipeline環境を構築する際に、トピックを作成しておかないとエラーになるため、別スタックとして先行作成
    aws cloudformation create-stack --stack-name MtcCfnSns --template-body file://mtc-cfn-sns.yaml --parameters ParameterKey=MailAddress,ParameterValue=xxx@gmail.com
    # aws cloudformation update-stack --stack-name MtcCfnSns --template-body file://mtc-cfn-sns.yaml --parameters ParameterKey=MailAddress,ParameterValue=xxx@gmail.com
    # aws cloudformation delete-stack --stack-name MtcCfnSns
    ```

1. Pipeline環境を構築

    ```sh
    aws cloudformation create-stack --stack-name MtcCfnPipeline --template-body file://mtc-cfn-pipeline.yaml --capabilities CAPABILITY_NAMED_IAM
    # aws cloudformation update-stack --stack-name MtcCfnPipeline --template-body file://mtc-cfn-pipeline.yaml --capabilities CAPABILITY_NAMED_IAM
    # aws cloudformation delete-stack --stack-name MtcCfnPipeline
    ```

1. Lambdaを構築

    ```sh
    # CAPABILITY_NAMED_IAM はIAMリソースを作成する時に明示的に指定が必要
    aws cloudformation create-stack --stack-name MtcCfnApi --template-body file://mtc-cfn-api.yaml --capabilities CAPABILITY_NAMED_IAM
    # aws cloudformation update-stack --stack-name MtcCfnApi --template-body file://mtc-cfn-api.yaml --capabilities CAPABILITY_NAMED_IAM
    # aws cloudformation delete-stack --stack-name MtcCfnApi
    ```

## 参考サイト

- CI/CD
  - `https://qiita.com/ytaka95/items/5899c44c85e71fdc5273`
