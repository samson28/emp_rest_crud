import 'dart:async';

import 'package:emp_front/model/employee.dart';
import 'package:emp_front/repository/employee_repository.dart';
import 'package:emp_front/repository/user_repository.dart';
import 'package:emp_front/screen/login.dart';
import 'package:emp_front/widget/employe_item_widget.dart';
import 'package:flutter/material.dart';

class EmployeList extends StatefulWidget {
  const EmployeList({super.key});

  @override
  State<EmployeList> createState() => _EmployeListState();
}

class _EmployeListState extends State<EmployeList> {
  List<Employe> employes = [];
  final searchController = TextEditingController();
  bool isLoading = false;
  bool isEmptyResponse = true;
  bool hasResponded = false;
  bool isResponseForDestination = false;
  Timer? searchOnStoppedTyping;

  @override
  void initState() {
    _getEmp();
    super.initState();
  }

  @override
  void dispose() {
    searchController.dispose();
    super.dispose();
  }

  // Future.delayed(
  //   const Duration(milliseconds: 500),
  //   () => setState(() {
  //     isLoading = false;
  //   }),
  // );

  _onChange(value) {
    setState(() {
      isLoading = true;
    });

    if (searchOnStoppedTyping != null) {
      setState(() => searchOnStoppedTyping?.cancel());
    }
    if (searchController.value.text != '') {
      setState(() => searchOnStoppedTyping =
          Timer(const Duration(seconds: 1), () => _rechercher(value)));
    } else {
      _getEmp();
      setState(() {
        isLoading = false;
      });
    }
  }

  _rechercher(String value) async {
    List<Employe> list = await EmployeRepo().shearchEmploye(value);
    setState(() {
      employes = list;
      isLoading = false;
    });
  }

  _getEmp() async {
    List<Employe> x = await EmployeRepo().getEmploye();
    setState(() {
      employes = x;
    });
  }

  delEmp(Employe employe) {
    EmployeRepo().deleteEmploye(employe);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text('Liste des Employées'),
        actions: [
          IconButton(
              onPressed: () async {
                await UserRepo().logout();
                Navigator.pushAndRemoveUntil(
                    context,
                    MaterialPageRoute<void>(
                        builder: (BuildContext context) => const Login()),
                    (route) => false);
              },
              icon: const Icon(Icons.logout))
        ],
      ),
      body: Column(
        children: [
          Padding(
            padding:
                const EdgeInsets.only(top: 5, bottom: 5, left: 10, right: 10),
            child: TextField(
                controller: searchController,
                onChanged: _onChange,
                decoration: InputDecoration(
                    border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(8.0),
                        borderSide: BorderSide.none),
                    hintText: 'Search',
                    filled: true,
                    prefixIcon: const Icon(Icons.search),
                    fillColor: Colors.grey)),
          ),
          isLoading
              ? const LinearProgressIndicator(
                  valueColor: AlwaysStoppedAnimation<Color>(Colors.white))
              : Container(),
          Expanded(
            child: employes.isEmpty
                ? const Center(
                    child: Text("Aucun Employé trouvé"),
                  )
                : ListView.builder(
                    itemCount: employes.length,
                    itemBuilder: (context, index) {
                      final employe = employes[index];
                      return Dismissible(
                          key: Key(employe.id.toString()),
                          onDismissed: (direction) {
                            setState(() {
                              delEmp(employe);
                              employes.remove(employe);
                            });
                            ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                                content: Text("${employe.nom} supprimé")));
                          },
                          background: Container(color: Colors.red),
                          child: EmployeItemWidget(
                            employe: employe,
                          ));
                    },
                  ),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => Navigator.pushNamed(context, '/addEmp'),
        child: const Icon(Icons.add),
      ),
    );
  }
}
//const BorderRadius.all(Radius.circular(5));