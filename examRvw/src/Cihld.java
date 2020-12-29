
class Child extends Parent {
  private int   privateChildField;
  protected int protectedChildField;
  public int    publicChildField;  
  
  public void main(String[] args) {



  	System.out.println( new Parent().protectedParentField + new Cousin().publicCousinField );
  }
}