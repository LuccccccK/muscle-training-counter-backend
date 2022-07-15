# README

1. DB構築

    ```sh
    aws cloudformation create-stack --stack-name MtcCfnDatabase --template-body file://mtc-cfn-database.yaml
    # aws cloudformation update-stack --stack-name MtcCfnDatabase --template-body file://mtc-cfn-database.yaml
    # aws cloudformation delete-stack --stack-name MtcCfnDatabase
    ```

1. Lambdaモジュール配置用のS3を構築

    ```sh
    aws cloudformation create-stack --stack-name MtcCfnS3ForLambda --template-body file://mtc-cfn-s3-for-lambda.yaml --capabilities CAPABILITY_NAMED_IAM
    # aws cloudformation update-stack --stack-name MtcCfnS3ForLambda --template-body file://mtc-cfn-s3-for-lambda.yaml --capabilities CAPABILITY_NAMED_IAM
    # aws cloudformation delete-stack --stack-name MtcCfnS3ForLambda
    ```

1. Lambdaモジュールをアップロード

    ```sh
    aws s3 cp ../app/target/app-1.0-SNAPSHOT.jar s3://s3-mtc-lambda/
    ```

1. Lambdaを構築

    ```sh
    # CAPABILITY_NAMED_IAM はIAMリソースを作成する時に明示的に指定が必要
    aws cloudformation create-stack --stack-name MtcCfnApi --template-body file://mtc-cfn-api.yaml --capabilities CAPABILITY_NAMED_IAM
    # aws cloudformation update-stack --stack-name MtcCfnApi --template-body file://mtc-cfn-api.yaml --capabilities CAPABILITY_NAMED_IAM
    # aws cloudformation delete-stack --stack-name MtcCfnApi
    ```
