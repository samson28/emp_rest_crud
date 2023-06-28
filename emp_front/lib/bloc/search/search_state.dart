part of 'search_bloc.dart';

class SearchState {
  bool isLoading;
  Timer? searchOnStoppedTyping;

  SearchState({required this.isLoading, required this.searchOnStoppedTyping});
}

class SearchInitial extends SearchState {
  SearchInitial() : super(isLoading: false, searchOnStoppedTyping: null);
}
