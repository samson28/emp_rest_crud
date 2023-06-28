import 'package:emp_front/bloc/employee/employee_bloc.dart';
import 'package:emp_front/model/employee.dart';
import 'package:emp_front/widget/employe_item_widget.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class EmployeeListWidget extends StatefulWidget {
  final List<Employe> employes;
  const EmployeeListWidget({super.key, required this.employes});

  @override
  State<EmployeeListWidget> createState() => _EmployeeListWidgetState();
}

class _EmployeeListWidgetState extends State<EmployeeListWidget> {

  _delEmp(Employe employe) {
    context.read<EmployeeBloc>().add(DeleteEmployeeEvent(employe: employe));
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
                    itemCount: widget.employes.length,
                    itemBuilder: (context, index) {
                      final employe = widget.employes[index];
                      return Dismissible(
                          key: Key(employe.id.toString()),
                          onDismissed: (direction) {
                            _delEmp(employe);
                            widget.employes.remove(employe);
                            ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                                content: Text("${employe.nom} supprimé")));
                          },
                          background: Container(color: Colors.red),
                          child: EmployeItemWidget(
                            employe: employe,
                          ));
                    },
                  );
  }
}