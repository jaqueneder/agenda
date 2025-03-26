fun main() {
    var escolhaMenu: Int
    val contatos = mutableListOf<Map<String, String>>()

    do {
        mostrarMenu()
        escolhaMenu = getChoice()

        when (escolhaMenu) {
            1 -> contatos.add(criarContato())
            2 -> listarContatos(contatos)
            3 -> removerContato(contatos)
            4 -> editarContato(contatos)
            0 -> println("\n📌 Saindo do programa... Obrigado por usar o gerenciador de contatos! 😊")
            else -> println("\n⚠️ Opção inválida! Tente novamente.")
        }
    } while (escolhaMenu != 0)
}

fun mostrarMenu() {
    println("\n========== 📞 MENU ========== ")
    println("1️⃣  Criar novo contato")
    println("2️⃣  Listar contatos")
    println("3️⃣  Remover contato existente")
    println("4️⃣  Editar contato existente")
    println("0️⃣  Sair")
    print("> ")
}

fun getChoice(): Int {
    return try {
        readln().toInt()
    } catch (e: NumberFormatException) {
        println("\n⚠️ Entrada inválida! Digite um número.")
        -1
    }
}

fun criarContato(): Map<String, String> {
    print("\n👤 Nome: ")
    val nome = readln().trim()

    print("📧 Email: ")
    val email = readln().trim()

    print("📞 Telefone: ")
    val telefone = readln().trim()

    println("\n✅ Contato cadastrado com sucesso!")

    return mapOf("nome" to nome, "email" to email, "telefone" to telefone)
}

fun listarContatos(contatos: List<Map<String, String>>) {
    if (contatos.isEmpty()) {
        println("\n📌 Nenhum contato foi cadastrado ainda.")
        return
    }

    println("\n📋 Lista de Contatos:")
    for ((index, contato) in contatos.withIndex()) {
        val nome = contato["nome"] ?: "Desconhecido"
        val email = contato["email"] ?: "Desconhecido"
        val telefone = contato["telefone"] ?: "Desconhecido"

        println("$index️⃣ - 👤 $nome | 📧 $email | 📞 $telefone")
    }
}

fun editarContato(contatos: MutableList<Map<String, String>>) {
    if (contatos.isEmpty()) {
        println("\n⚠️ Não há contatos para editar.")
        return
    }

    listarContatos(contatos)

    print("\n✏️ Digite o número do contato que deseja editar: ")
    val index = try {
        readln().toInt()
    } catch (e: NumberFormatException) {
        println("\n⚠️ Entrada inválida! Digite um número válido.")
        return
    }

    if (index !in contatos.indices) {
        println("\n⚠️ Índice inválido! Nenhum contato foi editado.")
        return
    }

    val contatoAntigo = contatos[index]

    println("\n🔄 Editando contato: ${contatoAntigo["nome"]}")

    print("👤 Novo Nome (ou pressione ENTER para manter '${contatoAntigo["nome"]}'): ")
    val novoNome = readln().trim().ifEmpty { contatoAntigo["nome"] ?: "Desconhecido" }

    print("📧 Novo Email (ou pressione ENTER para manter '${contatoAntigo["email"]}'): ")
    val novoEmail = readln().trim().ifEmpty { contatoAntigo["email"] ?: "Desconhecido" }

    print("📞 Novo Telefone (ou pressione ENTER para manter '${contatoAntigo["telefone"]}'): ")
    val novoTelefone = readln().trim().ifEmpty { contatoAntigo["telefone"] ?: "Desconhecido" }

    contatos[index] = mapOf("nome" to novoNome, "email" to novoEmail, "telefone" to novoTelefone)

    println("\n✅ Contato atualizado com sucesso!")
}

fun removerContato(contatos: MutableList<Map<String, String>>) {
    if (contatos.isEmpty()) {
        println("\n⚠️ Não há contatos para remover.")
        return
    }

    listarContatos(contatos)

    print("\n🗑️ Digite o número do contato que deseja remover: ")
    val index = try {
        readln().toInt()
    } catch (e: NumberFormatException) {
        println("\n⚠️ Entrada inválida! Digite um número válido.")
        return
    }

    if (index in contatos.indices) {
        val contatoRemovido = contatos.removeAt(index)
        println("\n✅ Contato '${contatoRemovido["nome"]}' removido com sucesso!")
    } else {
        println("\n⚠️ Índice inválido! Nenhum contato foi removido.")
    }
}
