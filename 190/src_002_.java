...  
public void setName (String name) {
  if(name == null)
    throw new IllegalArgumentException(
       "Name ust not be null);
  this.name = name;
}
...
