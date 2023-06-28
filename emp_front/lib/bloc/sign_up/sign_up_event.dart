part of 'sign_up_bloc.dart';

@immutable
abstract class SignUpEvent {}

class SignedUpEvent extends SignUpEvent {
  final User user;

  SignedUpEvent({required this.user});
}
