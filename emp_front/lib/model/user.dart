class User {
  final String id;
  final String name;
  final String email;
  final String password;
  final List<String> roles;

  User(
      {required this.id,
      required this.name,
      required this.email,
      required this.password,
      required this.roles});

  User.fromJson(Map<String, dynamic> json)
      : this(
            id: json['id'].toString(),
            name: json['name'].toString(),
            email: json['email'].toString(),
            password: '',
            roles: json['roles']);

  Map<String, dynamic> toJson() {
    return {'email': email, 'name': name, 'password': password, 'roles': roles};
  }
}
