import textwrap

def menu():
    menu_texto = """\n
    =================MENU=================
    [d]\tDepositar
    [s]\tSacar
    [e]\tExtrato
    [nc]\tNova conta
    [lc]\tListar contas
    [nu]\tNovo usuario
    [q]\t sair
    ==>"""
    return input(textwrap.dedent(menu_texto))

##3:27
def depositar(saldo, valor, extrato, /):
    if valor > 0:
        saldo += valor
        extrato +=f'Deposito:\tR$ {valor:2f}\n'
    else:
        print("Operação falhou")
    return saldo,extrato

def sacar(*, saldo, valor, extrato, limite, numero_saques, limite_saques):
    excedeu_saldo= valor > saldo
    excedeu_limite = valor > limite
    excedeu_saque = numero_saques >= limite_saques
    
    if excedeu_saldo:
        print("Saldo excedido")
    elif excedeu_saque:
        print("Saque excedido")
    elif excedeu_limite:
        print("Limite excedido")
    elif valor > 0:
        saldo -= valor
        extrato += f'Saque\t\tR$ {valor:.2f}\n'
        numero_saques +=1
        print("Saque realizado com sucesso")
    else:
        print("Erro")
    return saldo, extrato

def exibir_extrato(saldo, /,*, extrato):
    print("=======================EXTRATO=======================")
    print("Não foram realizadas movimentações" if not extrato else extrato)
    print(f'\nSaldo:\t\tR${saldo:.2f}')
    print("====================================================")
    
def criar_usuario(usuarios):
    cpf = input("Digite o cpf")
    usuario = filtrar_usuario(cpf,usuarios)
    
    if usuario:
        print("Usuario ja existe")
        return
    
    nome = input("Digite nome")
    data_nascimento = input("Nascimento")
    endereco = input("Endereço")
    usuarios.append({"nome":nome,"data_nascimento":data_nascimento,"cpf":cpf,"endereco":endereco})
    print("Usuario criado")
    
def filtrar_usuario(cpf, usuarios):
    usuarios_filtrados = [usuario for usuario in usuarios if usuario["cpf"] == cpf]
    return usuarios_filtrados[0] if usuarios_filtrados else None

def criar_conta(agencia,numero_conta,usuarios):
    cpf = input("Digite o cpf")
    usuario = filtrar_usuario(cpf,usuarios)
    
    if usuario:
        print("Conta criada com sucesso")
    return {"agencia": agencia, "numero_conta":numero_conta,"usuario":usuario}

def listar_conta(contas):
    for conta in contas:
        linha = f"""\
            Agencia:\t{conta['agencia']}
            C/C:\t\t{conta['numero_conta']}
            Titular:\t{conta['usuario']['nome']}
            """
        print("="*100)
        print(textwrap.dedent(linha))

def main():
    LIMITE_SAQUES = 3
    AGENCIA = "0001"
    
    saldo = 0
    limite = 500
    extrato = ""
    numero_saques = 0
    usuarios = []
    contas = []
    
    while True:
        opcao = menu()
        
        if opcao == "d":
            valor = float(input("Digite o valor do deposito: "))
            saldo, extrato = depositar(saldo, valor, extrato)
            
        elif opcao =="s":
            valor = float(input("Digite o valor do saque: "))
            saldo, extrato = sacar(
                saldo = saldo,
                valor = valor,
                extrato= extrato,
                limite = limite,
                numero_saques = numero_saques,
                limite_saques = LIMITE_SAQUES,
                )
            
        elif opcao == "e":
            exibir_extrato(saldo, extrato = extrato)
            
        elif opcao == "nu":
            criar_usuario(usuarios)
            
        elif opcao =="nc":
            numero_conta = len(contas)+1
            conta = criar_conta(AGENCIA,numero_conta,usuarios)
            
            if conta:
                contas.append(conta)
                
        elif opcao == "lc":
            listar_conta(contas)
        
        elif opcao == "q":
            break
            
main()