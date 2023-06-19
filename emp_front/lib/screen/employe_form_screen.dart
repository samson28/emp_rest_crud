import 'package:emp_front/model/employee.dart';
import 'package:emp_front/repository/employee_repository.dart';
import 'package:emp_front/screen/employe_list.dart';
import 'package:flutter/material.dart';

class EmployeFormScreen extends StatefulWidget {
  const EmployeFormScreen({super.key});

  @override
  State<EmployeFormScreen> createState() => _EmployeFormScreenState();
}

class _EmployeFormScreenState extends State<EmployeFormScreen> {
  final formKey = GlobalKey<FormState>();
  final nomController = TextEditingController();
  final prenomController = TextEditingController();
  final sexController = TextEditingController();
  final ageController = TextEditingController();
  final telController = TextEditingController();
  final mailController = TextEditingController();
  final fonctionController = TextEditingController();

  @override
  void dispose() {
    nomController.dispose();
    prenomController.dispose();
    sexController.dispose();
    ageController.dispose();
    telController.dispose();
    mailController.dispose();
    fonctionController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text("Ajouter un Employée"),
      ),
      body: SingleChildScrollView(
        physics: const ScrollPhysics(),
        child: Form(
          key: formKey,
          child: Card(
            child: Padding(
              padding: const EdgeInsets.all(5.0),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  const Text(
                    "Formulaire",
                    textAlign: TextAlign.center,
                    style: TextStyle(fontSize: 25),
                  ),
                  const SizedBox(
                    height: 35,
                  ),
                  TextFormField(
                      controller: nomController,
                      keyboardType: TextInputType.text,
                      decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        labelText: 'Nom',
                      ),
                      validator: (value) {
                        if (value!.isEmpty) {
                          return 'Veuillez entrer un nom';
                        }
                        return null;
                      }),
                  const SizedBox(
                    height: 15,
                  ),
                  TextFormField(
                      controller: prenomController,
                      keyboardType: TextInputType.text,
                      decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        labelText: 'Prenom',
                      ),
                      validator: (value) {
                        if (value!.isEmpty) {
                          return 'veuillez entrer un prenom';
                        }
                        return null;
                      }),
                  const SizedBox(
                    height: 15,
                  ),
                  TextFormField(
                      controller: sexController,
                      keyboardType: TextInputType.text,
                      decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        labelText: 'sex',
                      ),
                      validator: (value) {
                        if (value!.isEmpty) {
                          return 'Veuillez entrer le sex';
                        }
                        return null;
                      }),
                  const SizedBox(
                    height: 15,
                  ),
                  TextFormField(
                      controller: ageController,
                      keyboardType: TextInputType.number,
                      decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        labelText: 'Age',
                      ),
                      validator: (value) {
                        if (value!.isEmpty) {
                          return "Veuillez entrer l'age";
                        }
                        return null;
                      }),
                  const SizedBox(
                    height: 15,
                  ),
                  TextFormField(
                      controller: telController,
                      keyboardType: TextInputType.number,
                      decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        labelText: 'Tel',
                      ),
                      validator: (value) {
                        if (value!.isEmpty) {
                          return 'Veuillez entrer le numero de téléphone';
                        }
                        return null;
                      }),
                  const SizedBox(
                    height: 15,
                  ),
                  TextFormField(
                      controller: mailController,
                      keyboardType: TextInputType.emailAddress,
                      decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        labelText: 'Mail',
                      ),
                      validator: (value) {
                        if (value!.isEmpty) {
                          return 'Veuillez entrer le Mail';
                        }
                        return null;
                      }),
                  const SizedBox(
                    height: 15,
                  ),
                  TextFormField(
                      controller: fonctionController,
                      keyboardType: TextInputType.text,
                      decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        labelText: 'Fonction',
                      ),
                      validator: (value) {
                        if (value!.isEmpty) {
                          return 'Veuillez entrer la Fonction';
                        }
                        return null;
                      }),
                  const SizedBox(
                    height: 15,
                  ),
                  GestureDetector(
                    onTap: () async {
                      if (formKey.currentState!.validate()) {
                        Employe x = Employe(
                          id: 0,
                          nom: nomController.value.text,
                          prenom: prenomController.value.text,
                          sex: sexController.value.text,
                          age: int.parse(ageController.value.text),
                          tel: int.parse(telController.value.text),
                          mail: mailController.value.text,
                          fonction: fonctionController.value.text,
                        );
                        await EmployeRepo().addEmploye(x);
                        Navigator.pushAndRemoveUntil(
                            context,
                            MaterialPageRoute<void>(
                                builder: (BuildContext context) =>
                                    const EmployeList()),
                            (route) => false);
                      }
                    },
                    child: Container(
                      height: 50.0,
                      padding: const EdgeInsets.all(8.0),
                      margin: const EdgeInsets.symmetric(horizontal: 8.0),
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(5.0),
                        color: Colors.lightBlue[500],
                      ),
                      child: const Center(
                        child: Text('Save'),
                      ),
                    ),
                  )
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
