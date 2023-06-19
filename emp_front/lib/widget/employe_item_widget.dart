import 'package:emp_front/model/employee.dart';
import 'package:emp_front/screen/employe_details.dart';
import 'package:flutter/material.dart';

class EmployeItemWidget extends StatelessWidget {
  final Employe employe;
  const EmployeItemWidget({super.key, required this.employe});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () async {
        Navigator.push(
            context,
            MaterialPageRoute(
                builder: (context) => EmployeDetail(employe: employe)));
      },
      child: Card(
        margin: const EdgeInsets.all(8),
        elevation: 8,
        child: ListTile(
          tileColor: Colors.grey[200],
          leading: const CircleAvatar(child: Icon(Icons.person)),
          title: Text(employe.nom,
              style: Theme.of(context)
                  .textTheme
                  .titleMedium
                  ?.copyWith(color: Colors.indigo)),
          subtitle: Text(
            employe.prenom,
            style: const TextStyle(fontSize: 12),
          ),
          trailing: Text(
            employe.fonction,
            style: const TextStyle(
                color: Colors.black, fontSize: 15, fontWeight: FontWeight.bold),
          ),
        ),
      ),
    );
  }
}
