# Financial-transaction

    Aplicação criada para realizar transações de transferencia de saldos

## Documentation
    http://localhost:8080/swagger-ui.html#

## Usage

A aplicação pode ser visualizada pelo Console, nesta versão ela acessa um documento Json para fazer um exemplo de transferencia de saldo entre contas, com valores prefixados.

A mesma também pode ser consumida através de uma chamada postman com a seguinte curl e criar a base de dados sql localhost:3306/contas

table
    CREATE TABLE `saldos` (
    `conta` bigint NOT NULL,
    `saldo` double NOT NULL,
    PRIMARY KEY (`conta`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert inicial

    INSERT INTO `contas`.`saldos` (`conta`, `saldo`) VALUES ('938485762', '180');
    INSERT INTO `contas`.`saldos` (`conta`, `saldo`) VALUES ('347586970', '1200');
    INSERT INTO `contas`.`saldos` (`conta`, `saldo`) VALUES ('2147483649', '0');
    INSERT INTO `contas`.`saldos` (`conta`, `saldo`) VALUES ('675869708', '4900');
    INSERT INTO `contas`.`saldos` (`conta`, `saldo`) VALUES ('238596054', '478');
    INSERT INTO `contas`.`saldos` (`conta`, `saldo`) VALUES ('573659065', '787');
    INSERT INTO `contas`.`saldos` (`conta`, `saldo`) VALUES ('210385733', '10');
    INSERT INTO `contas`.`saldos` (`conta`, `saldo`) VALUES ('674038564', '400');
    INSERT INTO `contas`.`saldos` (`conta`, `saldo`) VALUES ('563856300', '1200');

Curl - postman

        curl --location --request GET 'http://localhost:8080contas' \
        --header 'Content-Type: application/json' \
        --data '[
            {
                "correlationId": 0,
                "datetime": "2023-09-09T14:15:05",
                "sourceAccount": 2147483649,
                "destinationAccount": 210385733,
                "amount": 149
            },
            {
                "correlationId": 3,
                "datetime": "2023-09-09T14:15:29",
                "sourceAccount": 347586970,
                "destinationAccount": 238596054,
                "amount": 1100
            },
            {
                "correlationId": 4,
                "datetime": "2023-09-09T14:14:00",
                "sourceAccount": 675869708,
                "destinationAccount": 210385733,
                "amount": 5300
            },
            {
                "correlationId": 5,
                "datetime": "2023-09-09T14:18:00",
                "sourceAccount": 238596054,
                "destinationAccount": 674038564,
                "amount": 1489
            },
                {
                "correlationId": 1,
                "datetime": " 2023-09-09T14:15:00",
                "sourceAccount": 938485762,
                "destinationAccount": 2147483649,
                "amount": 150
            },
            {
                "correlationId": 6,
                "datetime": "2023-09-09T14:18:20",
                "sourceAccount": 573659065,
                "destinationAccount": 563856300,
                "amount": 49
            },
            {
                "correlationId": 7,
                "datetime": "2023-09-09T14:19:00",
                "sourceAccount": 938485762,
                "destinationAccount": 2147483649,
                "amount": 44
            },
            {
                "correlationId": 8,
                "datetime": "2023-09-09T14:19:01",
                "sourceAccount": 573659065,
                "destinationAccount": 675869708,
                "amount": 150
            },
            {
                "correlationId": 9,
                "datetime": "2023-09-09T14:17:01",
                "sourceAccount": 210385733,
                "destinationAccount": 675869708,
                "amount": 500
            }
        ]'
