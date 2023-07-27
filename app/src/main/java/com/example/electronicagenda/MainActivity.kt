// Base class for contacts
open class Contact(val name: String, var phone: String) {
    override fun toString(): String {
        return "Name: $name, Phone: $phone"
    }
}

// Class for personal contacts
class PersonalContact(name: String, phone: String, val email: String) : Contact(name, phone) {
    override fun toString(): String {
        return "${super.toString()}, Email: $email"
    }
}

// Class for work contacts
class WorkContact(name: String, phone: String, val company: String) : Contact(name, phone) {
    override fun toString(): String {
        return "${super.toString()}, Company: $company"
    }
}

// Interface for the agenda
interface Agenda {
    fun addContact(contact: Contact)
    fun removeContact(name: String)
    fun editContact(name: String, newPhone: String)
    fun displayContacts()
}

// Implementation of the electronic agenda
class AgendaElectronica : Agenda {
    private val contactsList = mutableListOf<Contact>()

    // Add a contact to the agenda
    override fun addContact(contact: Contact) {
        contactsList.add(contact)
    }

    // Remove a contact from the agenda by name
    override fun removeContact(name: String) {
        val contactToRemove = contactsList.find { it.name == name }
        if (contactToRemove != null) {
            contactsList.remove(contactToRemove)
            println("Contact '$name' deleted.")
        } else {
            println("Contact not found '$name'.")
        }
    }

    // Edit a contact's phone number in the agenda by name
    override fun editContact(name: String, newPhone: String) {
        val contactToEdit = contactsList.find { it.name == name }
        if (contactToEdit != null) {
            contactToEdit.phone = newPhone
            println("Contact '$name' updated.")
        } else {
            println("Contact not found '$name'.")
        }
    }

    // Display all contacts in the agenda
    override fun displayContacts() {
        if (contactsList.isEmpty()) {
            println("The agenda is empty.")
        } else {
            println("Contacts in the address book:")
            contactsList.forEachIndexed { index, contact ->
                println("${index + 1}. $contact")
            }
        }
    }
}

fun main() {
    val agenda = AgendaElectronica()
    var option: Int

    do {
        println("\n--- Electronic Agenda ---")
        println("1. Add contact")
        println("2. Delete contact")
        println("3. Edit contact")
        println("4. Show contacts")
        println("5. Quit")
        print("Select an option: ")

        option = readLine()?.toIntOrNull() ?: 0

        when (option) {
            1 -> {
                print("Name: ")
                val name = readLine() ?: ""
                print("Phone: ")
                val phone = readLine() ?: ""
                print("Email or company (if personal or work): ")
                val emailOrCompany = readLine() ?: ""

                val contact = if (emailOrCompany.contains("@")) {
                    PersonalContact(name, phone, emailOrCompany)
                } else {
                    WorkContact(name, phone, emailOrCompany)
                }

                agenda.addContact(contact)
                println("Added contact.")
            }
            2 -> {
                print("Name of the contact to delete: ")
                val name = readLine() ?: ""
                agenda.removeContact(name)
            }
            3 -> {
                print("Name of the contact to edit: ")
                val name = readLine() ?: ""
                print("New phone: ")
                val newPhone = readLine() ?: ""
                agenda.editContact(name, newPhone)
            }
            4 -> {
                agenda.displayContacts()
            }
            5 -> println("Leaving...")
            else -> println("Invalid option, please try again.")
        }
    } while (option != 5)
}