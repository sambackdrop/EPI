#include <vector>

#define main _main
#include "sorted_arrays_merge.cc"
#undef main
#include "test_framework/generic_test.h"

using std::vector;

vector<int> SortKIncreasingDecreasingArray(const vector<int>& A) {
  // Decomposes A into a set of sorted arrays.
  vector<vector<int>> sorted_subarrays;
  typedef enum { kIncreasing, kDecreasing } SubarrayType;
  SubarrayType subarray_type = kIncreasing;
  int start_idx = 0;
  for (int i = 1; i <= size(A); ++i) {
    if (i == size(A) ||  // A is ended. Adds the last subarray.
        (A[i - 1] < A[i] && subarray_type == kDecreasing) ||
        (A[i - 1] >= A[i] && subarray_type == kIncreasing)) {
      if (subarray_type == kIncreasing) {
        sorted_subarrays.emplace_back(cbegin(A) + start_idx, cbegin(A) + i);
      } else {
        sorted_subarrays.emplace_back(crbegin(A) + size(A) - i,
                                      crbegin(A) + size(A) - start_idx);
      }
      start_idx = i;
      subarray_type = subarray_type == kIncreasing ? kDecreasing : kIncreasing;
    }
  }

  return MergeSortedArrays(sorted_subarrays);
}

// clang-format off


int main(int argc, char* argv[]) {
  std::vector<std::string> args {argv + 1, argv + argc};
  std::vector<std::string> param_names {"A"};
  return GenericTestMain(args, "sort_increasing_decreasing_array.cc", "sort_increasing_decreasing_array.tsv", &SortKIncreasingDecreasingArray, DefaultComparator{}, param_names);
}
// clang-format on
