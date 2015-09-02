# AndroidAbsListViewLoadMore
ListView/GridView添加加载更多

## Import
[JitPack](https://jitpack.io/)

Add it in your project's build.gradle at the end of repositories:

```gradle
repositories {
  // ...
  maven { url "https://jitpack.io" }
}
```

Step 2. Add the dependency in the form

```gradle
dependencies {
  compile 'com.github.vilyever:AndroidAbsListViewLoadMore:1.0.3'
}
```

## Usage
```java
AbsListView_VDLoadMore.addLoadMoreDelegate(listView, new AbsListView_VDLoadMore.LoadMoreDelegate() {
  @Override
  public void requireLoadMoreFromAbsListView(AbsListView absListView) {
    // TODO
  }
});
```

## License
[Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)

