class Employe {
  final int id;
  final String nom;
  final String prenom;
  final String sex;
  final int age;
  final int tel;
  final String mail;
  final String fonction;

  Employe(
      {required this.nom,
      required this.prenom,
      required this.sex,
      required this.age,
      required this.tel,
      required this.mail,
      required this.fonction,
      required this.id});

  Employe.fromJson(Map<String, dynamic> json)
      : this(
          nom: json['nom'].toString(),
          prenom: json['prenom'].toString(),
          sex: json['sex'].toString(),
          age: json['age'],
          tel: json['tel'],
          mail: json['mail'].toString(),
          fonction: json['fonction'].toString(),
          id: json['id'],
        );

  Map<String, dynamic> toJson() {
    return {
      'nom': nom,
      'prenom': prenom,
      'sex': sex,
      'age': age.toString(),
      'tel': tel.toString(),
      'mail': mail,
      'fonction': fonction,
    };
  }
}
