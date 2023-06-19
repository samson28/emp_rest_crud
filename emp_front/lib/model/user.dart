class User {
  final String name;
  final String email;
  final String password;

  User({required this.name, required this.email, required this.password});

  User.fromJson(Map<String, dynamic> json)
      : this(
            name: json['name'].toString(),
            email: json['email'].toString(),
            password: '');

  Map<String, dynamic> toJson() {
    return {'email': email, 'name': name, 'password': password};
  }
}
