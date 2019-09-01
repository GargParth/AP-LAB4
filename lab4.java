import java.util.*;

//Create  a sidekick for the Hero
class Sidekick implements Comparable<Sidekick>
{
    private Double HP;
    private int XP;
    private int xp_buffer;
    private Double attack_value;
    private Boolean can_clone;
    private ArrayList<Minion> my_clones_List;
    private int number_of_clones;



    //define the getters and setters

    /**
     * @return the number_of_clones
     */
    public int getNumber_of_clones() {
        return number_of_clones;
    }

    /**
     * @param number_of_clones the number_of_clones to set
     */
    public void setNumber_of_clones(int number_of_clones) {
        this.number_of_clones = number_of_clones;
    }


    /**
     * @return the can_clone
     */
    public Boolean getCan_clone() {
        return can_clone;
    }

    /**
     * @param my_clones_List the my_clones_List to set
     */
    public void setMy_clones_List(ArrayList<Minion> my_clones_List) {
        this.my_clones_List = my_clones_List;
    }

    /**
     * @param can_clone the can_clone to set
     */
    public void setCan_clone(Boolean can_clone) {
        this.can_clone = can_clone;
    }

    /**
     * @return the my_clones_List
     */
    public ArrayList<Minion> getMy_clones_List() {
        return my_clones_List;
    }

    /**
     * @return the attack_value
     */
    public Double getAttack_value() {
        return attack_value;
    }

    /**
     * @param attack_value the attack_value to set
     */
    public void setAttack_value(Double attack_value) {
        this.attack_value = attack_value;
    }

    /**
     * @return the xp_buffer
     */
    public int getXp_buffer() {
        return xp_buffer;
    }

    /**
     * @param xp_buffer the xp_buffer to set
     */
    public void setXp_buffer(int xp_buffer) {
        this.xp_buffer = xp_buffer;
    }


    /**
     * @return the hP
     */
    public Double getHP() {
        return HP;
    }

    /**
     * @return the xP
     */
    public int getXP() {
        return XP;
    }

    /**
     * @param hP the hP to set
     */
    public void setHP(Double hP) {
        HP = hP;
    }

    /**
     * @param xP the xP to set
     */
    public void setXP(int xP) {
        XP = xP;
    }


    /**
     * Add the XP points of the Sidekick
     * @param exp
     */
    public void addXP(int exp)
    {
        int counter=0;
        exp = exp + this.xp_buffer;
        for(int i=0; i<exp; i++)
        {
            this.XP += 1;
            ++counter;
            if(counter==5)
            {
                this.attack_value+=1;
                counter=0;
            }
        }
        this.xp_buffer = counter;
    }



    @Override
    public int compareTo(Sidekick ob2) {
        if( this.getXP() < ob2.getXP() ) return -1;
        if( this.getXP() > ob2.getXP() ) return  1;
        else return 0;
    }    

    public void Sidekick_attack( Sidekick mysidekick, Monster myMonster )
    {
        Double newMonsterhp = myMonster.getHP() - ( mysidekick.getAttack_value() );
        System.out.println("SideKick Attacked and inflicted "+ mysidekick.getAttack_value()+" damage to the monster" );
        myMonster.setHP( newMonsterhp );
    }

}


class Minion extends Sidekick implements Cloneable
{

    //Constructor for minion class

    Minion(int exp)
    {
        super.setCan_clone(true);
        super.setMy_clones_List( new ArrayList<Minion>() );
        super.setHP( Double.parseDouble( String.valueOf(100) ) );

        int xp_for_attack = exp - 5;

        super.setAttack_value( Double.parseDouble( String.valueOf( 1 + ( xp_for_attack * 0.5 ) ) ) );
        super.setXP( exp -5 );
        super.setXp_buffer(0);
        super.setNumber_of_clones(1);
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this.clone();
    }
}



class Knight extends Sidekick
{
    Knight(int exp)
    {
        super.setCan_clone(false);
        super.setMy_clones_List( new ArrayList<Minion>() );
        super.setNumber_of_clones(1);
        super.setHP( Double.parseDouble( String.valueOf( 100 ) ) );
        super.setXP(exp-8);
        super.setXp_buffer(0);
        int xp_for_attack = exp - 8;
        super.setAttack_value( Double.parseDouble( String.valueOf( 2 + ( xp_for_attack*0.5 ) ) ) );
    }
}


//HERO CLASS DEFINED AHEAD
class Hero
{
    private final String HeroType;
    private Double HP;
    private int XP;
    private int Current_level;
    private boolean Special_attack;
    private boolean Special_usable;
    private int moves_since_last_special;
    private int Base_attack;
    private int Base_defense;
    private boolean Defending;
    private int hero_level;
    private int max_hp;
    private ArrayList<Sidekick> MySideKicks = new ArrayList<Sidekick>();
    private Sidekick using_sidekick;

    /**
     * @return the using_sidekick
     */
    public Sidekick getUsing_sidekick() {
        return using_sidekick;
    }

    /**
     * @param using_sidekick the using_sidekick to set
     */
    public void setUsing_sidekick(Sidekick using_sidekick) {
        this.using_sidekick = using_sidekick;
    }

    /**
     * @return the mySideKicks
     */
    public ArrayList<Sidekick> getMySideKicks() {
        return MySideKicks;
    }

    /**
     * @param mySideKicks the mySideKicks to set
     */
    public void setMySideKicks(ArrayList<Sidekick> mySideKicks) {
        MySideKicks = mySideKicks;
    }

    /**
     * @return the max_hp
     */
    public int getMax_hp() {
        return max_hp;
    }

    /**
     * @param max_hp the max_hp to set
     */
    public void setMax_hp(int max_hp) {
        this.max_hp = max_hp;
    }


    public void Reset_hero()
    {
        this.HP = Double.parseDouble( String.valueOf(100) );
        this.XP = 0;
        this.Current_level=1;
        this.Special_attack = false;
        this.Special_usable = false;
        this.moves_since_last_special = 0;
        this.Defending=false;
        this.hero_level = 1;
        this.max_hp=100;
    }




    //PUTTING GETTERS AND SETTERS


    /**
     * @return the hero_level
     */
    public int getHero_level() {
        return hero_level;
    }

    /**
     * @param hero_level the hero_level to set
     */
    public void setHero_level(int hero_level) {
        this.hero_level = hero_level;
    }


    /**
     * @return the special_usable
     */
    public boolean isSpecial_usable() {
        return Special_usable;
    }

    /**
     * @param moves_since_last_special the moves_since_last_special to set
     */
    public void setMoves_since_last_special(int Moves_since_last_special) {
        this.moves_since_last_special = Moves_since_last_special;
        if(this.moves_since_last_special==3)
        {
            this.Special_usable=true;
            this.moves_since_last_special=0;
            this.setSpecial_attack(false);
        }
    }

    /**
     * @param defending the defending to set
     */
    public void setDefending(boolean defending) {
        Defending = defending;
    }

    /**
     * @param special_attack the special_attack to set
     */
    public void setSpecial_attack(boolean special_attack) {
        Special_attack = special_attack;
    }

    /**
     * @param special_usable the special_usable to set
     */
    public void setSpecial_usable(boolean special_usable) {
        Special_usable = special_usable;
    }

    /**
     * @return the moves_since_last_special
     */
    public int getMoves_since_last_special() {
        return moves_since_last_special;
    }


    /**
     * @return the base_attack
     */
    public int getBase_attack() {
        return Base_attack;
    }

    /**
     * @return the defending
     */
    public boolean isDefending() {
        return Defending;
    }

    /**
     * @return the base_defense
     */
    public int getBase_defense() {
        return Base_defense;
    }

    /**
     * @param base_attack the base_attack to set
     */
    public void setBase_attack(int base_attack) {
        Base_attack = base_attack;
    }

    /**
     * @param base_defense the base_defense to set
     */
    public void setBase_defense(int base_defense) {
        Base_defense = base_defense;
    }

    /**
     * @return the current_level
     */
    public int getCurrent_level() {
        return Current_level;
    }

    /**
     * @return the special_attack
     */
    public boolean isSpecial_attack() {
        return Special_attack;
    }

    /**
     * @return the hP
     */
    public Double getHP() {
        return HP;
    }
    
    /**
     * @return the HeroType
     */
    public String getHeroType() {
        return HeroType;
    }

    /**
     * @return the xP
     */
    public int getXP() {
        return XP;
    }

    /**
     * @param xP the xP to set
     */
    public void setXP(int xP) {
        XP = xP;
        if(this.getXP() >=20 && this.getXP()<60)
        {
            //hero leveled up to level 2
            this.setHero_level(2);
            this.setHP( Double.parseDouble( String.valueOf(150) ) );
            this.setMax_hp(150);
            System.out.println("Level UP: level 2");
        }
        else if(this.getXP()>=60 && this.getXP()<120)
        {
            //hero is now level 3
            this.setHero_level(3);
            this.setHP( Double.parseDouble( String.valueOf(200) ) );
            this.setMax_hp(200);
            System.out.println("Level UP: level 3");
        }
        else if(this.getXP()>=120)
        {
            this.setHero_level(4);
            this.setHP( Double.parseDouble( String.valueOf(250) ) );
            this.setMax_hp(250);
            System.out.println("Level UP: level 4");
        }
    }

    /**
     * @param current_level the current_level to set
     */
    public void setCurrent_level(int current_level) {
        Current_level = current_level;
    }

    /**
     * @param hP the hP to set
     */
    public void setHP(Double hP) {
        HP = hP;
    }


    Hero(String heroType)
    {
        this.HeroType = heroType.toLowerCase();
        this.Current_level = 1;
        this.HP = Double.parseDouble( String.valueOf(100) );
        this.XP = 0;
        this.Special_attack = false;
        this.moves_since_last_special = 0;
        this.Defending = false;
        this.Special_usable=false;
        this.Special_attack=false;
        this.max_hp=100;
    }


    public void add_sidekick(Sidekick s)
    {
        getMySideKicks().add(s);

        if( s instanceof Minion )
        {
            System.out.println("You bought a sidekick: Minion");
        }
        else
        {
            System.out.println("You bought a sidekick: Knight");
        }
        System.out.println("XP of sidekick is "+ s.getXP() );
        System.out.println("Attack of sidekick is "+ s.getAttack_value());
    }

    /**
     * Changes the HP of monster and hero in accordance to the type of attack,
     * The type of Hero and the use of special power
     * @param myhero
     * @param mymonster
     * @param mysideKick
     */
    
     public void AttackMonster(Hero myhero, Monster mymonster, Sidekick mysideKick)
    {
        Double newMonsterhp = mymonster.getHP()-(myhero.getBase_attack());
        //check if the attacking hero is warrior
        if(myhero.getHeroType().toLowerCase().equals("Warrior".toLowerCase()) && isSpecial_attack())
        {
            System.out.println("Base attack boosted by 5, Special attack is being used");
            //The selected Hero is warrior and is attacking with special power activated
                newMonsterhp -= 5;
        }
        //check if the attacking hero is a Mage
        else if(myhero.getHeroType().toLowerCase().equals("Mage".toLowerCase()) && isSpecial_attack())
        {
            System.out.println("Opponents HP reduced by "+ ( newMonsterhp*0.05 ) +" due to special power");
            newMonsterhp = newMonsterhp*0.95;
        }
        //check if the attacking Hero is a Theif
        else if(myhero.getHeroType().toLowerCase().equals("Thief".toLowerCase()) && isSpecial_attack())
        {
            myhero.setHP( myhero.getHP() + newMonsterhp*0.30 );
            newMonsterhp = newMonsterhp*0.70;
        }
        //check if the attacking hero is Healer
        else if(myhero.getHeroType().toLowerCase().equals("Healer".toLowerCase()) && isSpecial_attack())
        {
            System.out.println("Your HP increased by "+( myhero.getHP()*0.05 )+" due to Special power");
            myhero.setHP(myhero.getHP()*1.05);
        }
        
        System.out.println("You attacked and inflicted "+(mymonster.getHP()-newMonsterhp)+" damage to the monster");
        mymonster.setHP(newMonsterhp);

        if( mysideKick!=null )
        {
            //the sidekick will also attack now
            for( int i=0; i< mysideKick.getNumber_of_clones();i++ )
            {
                if(mysideKick.getHP()>0)
                    mysideKick.Sidekick_attack( mysideKick , mymonster);
                else
                    System.out.println("Your Sidekick is Dead");
            }
        }

        System.out.println("Your HP: "+myhero.getHP()+"/"+myhero.getMax_hp() +" Monster HP: "+ mymonster.getHP()+"/"+mymonster.getMax_hp());
        
    }
}


class Warrior extends Hero
{    
    /**
     * 
     * @param heroType
     */
    Warrior(String heroType)
    {
        super(heroType);
        super.setBase_attack(10);
        super.setBase_defense(3);
    }

    @Override
    public void Reset_hero() {
        super.Reset_hero();
        super.setBase_attack(10);
        super.setBase_defense(3);
    }

}

class Mage extends Hero
{   
    /**
     * 
     * @param heroType
     */
    Mage(String heroType)
    {
        super(heroType);
        super.setBase_attack(5);
        super.setBase_defense(5);
    }

    @Override
    public void Reset_hero() {
        super.Reset_hero();
        super.setBase_attack(5);
        super.setBase_defense(5);
    }

}

class Thief extends Hero
{   
    /**
     * 
     * @param heroType
     */
    Thief(String heroType)
    {
        super(heroType);
        super.setBase_attack(6);
        super.setBase_defense(4);
    }
    
    //think over this function, it will implement polymorphism
    @Override
    public void setMoves_since_last_special(int Moves_since_last_special) {
        super.setMoves_since_last_special(Moves_since_last_special);
        if(Moves_since_last_special==1)
        {
            super.setSpecial_attack(false);
        }
    }

    @Override
    public void Reset_hero() {
        super.Reset_hero();
        super.setBase_attack(6);
        super.setBase_defense(4);
    }
}

class Healer extends Hero
{   
    /**
     * 
     * @param heroType
     */
    Healer(String heroType)
    {
        super(heroType);
        super.setBase_attack(4);
        super.setBase_defense(8);
    }

    @Override
    public void Reset_hero() {
        super.Reset_hero();
        super.setBase_attack(4);
        super.setBase_defense(8);
    }
}

class Monster
{
    private Double HP;
    private Double max_hp;
    private final int level;

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * @return the hP
     */
    public Double getHP() {
        return HP;
    }

    /**
     * @param hP the hP to set
     */
    public void setHP(Double hP) {
        HP = hP;
    }

    /**
     * @return the max_hp
     */
    public Double getMax_hp() {
        return max_hp;
    }

    /**
     * @param max_hp the max_hp to set
     */
    public void setMax_hp(Double max_hp) {
        this.max_hp = max_hp;
    }

   

    
    public Double getMonsterAttackValue( Double hp )
    {
        Random rand = new Random();
        Double constraint = hp/4;
        Double attack = (Double)rand.nextGaussian();

        while( attack<-1 || attack>1 )
        {
            attack = (Double)rand.nextGaussian();
        }

        return (Double)(Math.abs((double)attack)*constraint);
    }

    Monster(int mylevel)
    {
        this.level = mylevel;
        switch(mylevel)
        {
            case 1 : this.HP = Double.parseDouble( String.valueOf(100) );
                    break;
            case 2 : this.HP = Double.parseDouble( String.valueOf(150) );
                    break;
            case 3 : this.HP = Double.parseDouble( String.valueOf(200) );
                    break;
            case 4 : this.HP = Double.parseDouble( String.valueOf(250) );
                    break;
        }
    }


    public void attackHero( Hero myHero, Monster myMonster )
    {
        Double monster_attack_value = getMonsterAttackValue(myMonster.getHP());
        //if the monster is LIONFANG
        //accomodate his special attack 
        boolean LionFang = false;

        if(myMonster.getLevel()==4)
        {
            LionFang = true;
            //The monster is LIONFANG
            Random rand = new Random();
            int x = rand.nextInt(10);
            if(x==2)
            {
                //THis happens with 1/10 probablility
                monster_attack_value = (Double)((Double)myHero.getHP()*0.5);
            }
        }

        if(LionFang)
        {
            System.out.println("LionFang Attacks!");
        }
        else
        {
            System.out.println("Monster Attacks");
        }

        Double final_attack_val = monster_attack_value;
        if(myHero.isDefending())
        {
            //The hero is defending, get its base Defense
            int defended_Value = myHero.getBase_defense();
            Double net_attack = monster_attack_value - defended_Value;

            //check the type of hero being acted on and the applicability of any special power
            if(myHero.getHeroType().toLowerCase().equals("Warrior".toLowerCase()) && myHero.isSpecial_attack())
            {
                net_attack -= 5;
            }
            else if(myHero.getHeroType().toLowerCase().equals("Mage".toLowerCase()) && myHero.isSpecial_attack())
            {
                myMonster.setHP( (Double)(myMonster.getHP()*0.95) );
            }
            else if(myHero.getHeroType().toLowerCase().equals("Theif".toLowerCase()) && myHero.isSpecial_attack())
            {
                //increase the hero HP
                myHero.setHP( myHero.getHP()+(myMonster.getHP()*0.30) );
                //Decrease the Monster HP by 30%
                myMonster.setHP( myMonster.getHP()*0.70 );
            }
            else if(myHero.getHeroType().toLowerCase().equals("Healer".toLowerCase()) && myHero.isSpecial_attack())
            {
                //increase your own HP 
                myHero.setHP( (Double)(myHero.getHP()*1.05) );
            }

            if(net_attack>0)
            {
                //The net attack is non negative meaning that the hero has taken some amount of damage
                final_attack_val = net_attack;
            }
        }
        if(LionFang)
        {
            System.out.println("Lionfang inflicted "+final_attack_val+" Damage");
        }
        else
        {
            System.out.println("Monster innflicted "+final_attack_val+" Damage");
        }
        
        
        //add the effect of sidekick Knight with Monster Zombie
        if( myMonster instanceof Zombie )
        {
            if( myHero.getUsing_sidekick() !=null && myHero.getUsing_sidekick() instanceof Knight)
            {
                final_attack_val -= 5;
            }
        }
        
        //change the HP of hero And monster respectively
        
        myHero.setHP( (Double)(myHero.getHP() - final_attack_val) );

        //The monster Attack also influences the sidekick, take that into account
        if( myHero.getUsing_sidekick()!=null && myHero.getUsing_sidekick().getHP()>=0 )
        {
            Sidekick mysidekick = myHero.getUsing_sidekick();
            Double s_hp = mysidekick.getHP();
            s_hp -= (1.5)*final_attack_val;
            mysidekick.setHP(s_hp);

            for ( int i=0; i<mysidekick.getNumber_of_clones(); i++ )
            {
                System.out.println("SideKick HP: "+ mysidekick.getHP()+"/100" );
            }
        }

        System.out.println("Your HP: "+myHero.getHP()+"/"+myHero.getMax_hp() +" Monster HP: "+ myMonster.getHP()+"/"+myMonster.getMax_hp());
    }

}


class Goblin extends Monster
{
    Goblin()
    {
        super(1);
        super.setMax_hp( Double.parseDouble( String.valueOf(100) ) );
    }
}


class Zombie extends Monster
{
    Zombie()
    {
        super(2);
        super.setMax_hp( Double.parseDouble( String.valueOf(100) ) );
    }
}


class Fiend extends Monster
{
    Fiend()
    {
        super(3);
        super.setMax_hp( Double.parseDouble( String.valueOf(200) ) );
    }
}


class LionFang extends Monster
{
    LionFang()
    {
        super(4);
        super.setMax_hp( Double.parseDouble( String.valueOf(250) ) );
    }
}



//PLAYER CLASS DEFINED AHEAD
class Player
{
    private final Hero myHero;
    private final String UserName;
    private Location current_location;
    private ArrayList<Location> Path_taken;

    Player(String usrname, Hero hero)
    {
        this.myHero = hero;
        this.UserName = usrname;
        this.current_location = null;
        this.Path_taken = new ArrayList<Location>();
    }

    /**
     * @return the path_taken
     */
    public ArrayList<Location> getPath_taken() {
        return Path_taken;
    }

    /**
     * @return the current_location
     */
    public Location getCurrent_location() {
        return current_location;
    }

    /**
     * @return the myHero
     */
    public Hero getMyHero() {
        return myHero;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * @param current_location the current_location to set
     */
    public void setCurrent_location(Location current_location) {
        this.current_location = current_location;
    }

}


//Create a class to keep track of locations
class Location
{
    private Monster local_monster;
    private int Location_number;

    Location(int num)
    {
        this.Location_number = num;
        Random rand = new Random();
        num = rand.nextInt(9);
        switch(num)
        {
            case 0:
            case 1:
            case 2: local_monster = new Goblin();
                    break;
            case 3:
            case 4:
            case 5: local_monster = new Fiend();
                    break;
            case 6:
            case 7:
            case 8: local_monster = new Zombie();
                    break;
        }

        if(this.Location_number==10)
        {
            //This is the location for LionFang
            local_monster = new LionFang();
        }
    }

    /**
     * @return the local_monster
     */
    public Monster getLocal_monster() {
        return local_monster;
    }

    /**
     * @return the location_number
     */
    public int getLocation_number() {
        return Location_number;
    }

}


class GameplayEngine
{
    ArrayList<Location> availaible_locs = new ArrayList<Location>();
    HashMap<Location, Integer> location_numbers_to_display = new HashMap<Location, Integer>();
    HashMap<Integer, Location> Number_displayed_to_Location = new HashMap<Integer, Location>();
    ArrayList<Player> players = new ArrayList<Player>();
    HashMap<Player, Boolean> GameInitialized = new HashMap<Player, Boolean>();


    /**
     * Initialize the gameplay variables for the Player p
     * initialize the Locations, Set current location of player to starting location 0
     * @param p : player for which the engine is initialized
     */
    public void initialize_Engine(Player p)
    {
        for ( int i=0; i<11; i++ )
        {
            Location L = new Location(i);
            availaible_locs.add(L);
            
            //set the current location of this player as the start Location
            if(L.getLocation_number()==0)
            {
                p.setCurrent_location(L);
            }
        }

        boolean[] usedNumbers = new boolean[11];
        Random rand = new Random();
        int i=0;
        //Now set the values of the next nodes that are visible from each node
        while(i!=availaible_locs.size())
        {
            int x = rand.nextInt(11);
            if(usedNumbers[x]==false)
            {
                location_numbers_to_display.put( availaible_locs.get(i) ,x);
                Number_displayed_to_Location.put(x, availaible_locs.get(i));
                usedNumbers[x]=true;
                i+=1;
            } 
        }

    }

    public void Display_Startup_menu()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to ArchLegends");
        System.out.println("Choose your option");
        System.out.println("1) New User");
        System.out.println("2) Existing User");
        System.out.println("3) Exit");
        int user_choice = Integer.parseInt(sc.nextLine());
        switch(user_choice)
        {
            case 1: Register_new_user();
                    break;
            case 2: //Existing user
                    //login with username and then use the corresponding player instance to
                    //play the game
                    Existing_User_login();
                    break;
            case 3: System.exit(0);
                    break;
            default://Error Handling
                    System.out.println("ERROR : Please Enter A valid option!");
                    Display_Startup_menu();
                    break;
            
        }

    }



    public void create_clones(Player p)
    {
        p.getMyHero().getUsing_sidekick().setCan_clone(false);
        p.getMyHero().getUsing_sidekick().setNumber_of_clones(4);
    }


    /**
     * 
     * @param p
     * @param exp
     * @return the exp left with the hero of the player after buying the sidekick
     */
    public int Buy_Sidekick(Player p, int exp)
    {
        Scanner sc = new Scanner( System.in );
        System.out.println("XP available to buy Sidekick: "+ exp );
        System.out.println("If you want to buy a Minion, press 1");
        System.out.println("If you want to buy a Knight, press 2");
        int choice = Integer.parseInt( sc.nextLine() );
        System.out.println("Enter the Amount of XP to spend: ");
        int s_xp = Integer.parseInt( sc.nextLine() );
        Sidekick mysidekick = null;
        switch(choice)
        {
            case 1: //Player chose to buy a Minion sidekick
                    mysidekick = new Minion( s_xp );
                    p.getMyHero().add_sidekick( mysidekick );
                    break;
            case 2: //Player chose to buy a Knight Sidekick
                    mysidekick = new Knight( s_xp );
                    p.getMyHero().add_sidekick( mysidekick );
                    break;
            default: System.out.println("Please Enter Correct Value of choice for type of sidekick");
                    sc.nextLine();
                    Buy_Sidekick(p, exp);
        }

        return exp-s_xp;
    }


    public void use_sidekick(Player p)
    {
        //First remove those sideKicks whose HP is 0
        ArrayList<Sidekick> myList = p.getMyHero().getMySideKicks();
        ArrayList<Sidekick> temp_list = new ArrayList<Sidekick>();

        for(Sidekick s: myList)
        {
            if(s.getHP()<=0)
            {
                temp_list.add(s);
            }
        }

        for ( Sidekick s : temp_list )
        {
            myList.remove(s);
        }

        if(myList.size()!=0)
        {
            Collections.sort( p.getMyHero().getMySideKicks() );

            Sidekick toUse = p.getMyHero().getMySideKicks().get( p.getMyHero().getMySideKicks().size() -1 );

            p.getMyHero().setUsing_sidekick( toUse );
            if ( toUse instanceof Minion )
            {
                System.out.println("You have a sidekick Minion with you. Attack of sidekick is "+toUse.getAttack_value());
            }
            else if( toUse instanceof Knight)
            {
                System.out.println("You have a sidekick Knight with you. Attack of sidekick is "+toUse.getAttack_value());
            }
        }
        else
        {
            System.out.println("You dont have any available sidekick");
            p.getMyHero().setUsing_sidekick(null);
        }
    }


    public int Fight_monster_at_current_location(Player p)
    {
        boolean s_in_use = false;
        Scanner sc = new Scanner(System.in);
        Double init_hero_hp = p.getMyHero().getHP();
        boolean win = false;
        System.out.println();
        System.out.println("Fight Started. You are fighting a level "+p.getCurrent_location().getLocal_monster().getLevel()+" Monster");

        System.out.println("Type yes if you wish to use a sidekick, else type no");
        String use_sk = sc.nextLine();
        if( use_sk.toLowerCase().equals( "Yes".toLowerCase() ) )
        {
            use_sidekick(p);
            s_in_use = true;
            if( p.getMyHero().getUsing_sidekick()!=null )
            {
                if( p.getMyHero().getUsing_sidekick().getCan_clone() == true)
                {
                    //give option to clone
                    System.out.println("Press c to use cloning ability. Else press f to move to the fight");
                    String cloner = sc.nextLine();
                    if(cloner.equals("c"))
                    {
                        create_clones(p);
                    }
                }
            }
        }


        while(p.getMyHero().getHP()>0 && p.getCurrent_location().getLocal_monster().getHP()>0)
        {
            //this loop runs while the monster and the Hero both have Health Points greater than 0
            System.out.println("Choose Move");
            System.out.println("1) Attack");
            System.out.println("2) Defense");
            if(p.getMyHero().isSpecial_usable())
            {
                System.out.println("3) Special Attack");
            }
            //implement the counter for the Special attack
            int user_choice = Integer.parseInt(sc.nextLine());
            switch(user_choice)
            {
                case 1://The user chose to attack
                        System.out.println("You choose to Attack");
                        if(s_in_use)
                            p.getMyHero().AttackMonster(p.getMyHero(), p.getCurrent_location().getLocal_monster(),  p.getMyHero().getUsing_sidekick());
                        else
                            p.getMyHero().AttackMonster(p.getMyHero(), p.getCurrent_location().getLocal_monster(),  null);
                        p.getMyHero().setMoves_since_last_special(p.getMyHero().getMoves_since_last_special()+1);
                        break;
                case 2://The user chose to Defend
                        p.getMyHero().setDefending(true);
                        p.getMyHero().setMoves_since_last_special(p.getMyHero().getMoves_since_last_special()+1);
                        break;
                case 3:if(p.getMyHero().isSpecial_usable())
                        {
                            p.getMyHero().setSpecial_attack(true);
                            p.getMyHero().setSpecial_usable(false);
                            System.out.println("Special power activated");
                            System.out.println("Performing Special attack");
                            if( p.getMyHero().getHeroType().toLowerCase().equals("Thief".toLowerCase()))
                            {
                                //thif used his special attack
                                System.out.println("You have stolen "+ p.getCurrent_location().getLocal_monster().getHP()*0.30 + " HP from the Monster");
                            }
                        }
                        else
                        {
                            System.out.println("Special attack currently not usable");
                            continue;
                        }
            }
           
            if(p.getCurrent_location().getLocal_monster().getHP()<=0)
            {
                //The monster is dead
                //Increase Hero XP
                //Increase hero Base levels
                //set win to True
                //Reset the monster HP
                System.out.println("Monster Killed");
                System.out.println( p.getCurrent_location().getLocal_monster().getLevel()*20 + " XP awarded" );
                

                int exp_points = p.getCurrent_location().getLocal_monster().getLevel()*20;
                System.out.println("If you would like to buy a sidekick, type yes. Else type no to upgrade level.");
                String sidekick_choice = sc.nextLine().toLowerCase();
                if( sidekick_choice.equals( "yes".toLowerCase() ) )
                {
                    exp_points = Buy_Sidekick(p, exp_points);
                }


                p.getMyHero().setXP( p.getMyHero().getXP() + exp_points);
                if(p.getMyHero().getUsing_sidekick()!=null)
                {
                    //The hero had used a sidekick in his battle
                    p.getMyHero().getUsing_sidekick().addXP( exp_points/10 );
                    p.getMyHero().getUsing_sidekick().setNumber_of_clones(1);
                }
                win = true;
                Double monHP = Double.parseDouble( String.valueOf(0) );
                switch( p.getCurrent_location().getLocal_monster().getLevel())
                {
                    case 1: monHP = Double.parseDouble( String.valueOf(100) );
                            break;
                    case 2: monHP = Double.parseDouble( String.valueOf(150) );
                            break;
                    case 3: monHP = Double.parseDouble( String.valueOf(200) );
                            break;
                    case 4: monHP = Double.parseDouble( String.valueOf(250) );
                }
                p.getCurrent_location().getLocal_monster().setHP(monHP);
                p.getMyHero().setHP( Double.parseDouble( String.valueOf( p.getMyHero().getMax_hp()) ) );
                

                if( p.getCurrent_location().getLocal_monster().getLevel()==4 )
                {
                    //The player just won fromm Lionfang
                    System.out.println("Congratulations, LIONFANG defeated, You Won");
                    sc.nextLine();
                    Display_Startup_menu();
                }
                break;
            }
            
            //Now the monster attacks;
            p.getCurrent_location().getLocal_monster().attackHero( p.getMyHero(),  p.getCurrent_location().getLocal_monster());

            if(p.getMyHero().getHP()<=0)
            {
                //you lost
                win = false;
                System.out.println("You Lost The Fight, Resetting the gameplay ");
                //reset the state of the monster
                Double monHP = Double.parseDouble( String.valueOf(0) );
                switch( p.getCurrent_location().getLocal_monster().getLevel())
                {
                    case 1: monHP = Double.parseDouble( String.valueOf(100) );
                            break;
                    case 2: monHP = Double.parseDouble( String.valueOf(150) );
                            break;
                    case 3: monHP = Double.parseDouble( String.valueOf(200) );
                            break;
                    case 4: monHP = Double.parseDouble( String.valueOf(250) );
                }
                p.getCurrent_location().getLocal_monster().setHP(monHP);
                //reset the state of the Hero
                p.getMyHero().Reset_hero();
                //send hero to location 0
                p.setCurrent_location(availaible_locs.get(0));
                break;
            }

            

        }

        if(win)
            {
                return 1;
            }
            return -1;
    }

    public void Play_Game(Player p)
    {
        Scanner sc = new Scanner(System.in);
        if(p.getCurrent_location().getLocation_number()==0)
        {
            //player is at the starting location, ask for new loc
            System.out.println("You are at the starting location. Choose path:");
            System.out.println("1) Go to Location "+location_numbers_to_display.get(availaible_locs.get(1)));
            System.out.println("2) Go to Location "+location_numbers_to_display.get(availaible_locs.get(2)));
            System.out.println("3) Go to Location "+location_numbers_to_display.get(availaible_locs.get(3)));
            System.out.println("Enter -1 to Exit");
            int whereTo = Integer.parseInt(sc.nextLine());

            int dec;
            if(whereTo==-1)
            {
                System.out.println("Exiting... ");
                System.exit(0);   
            }
            switch(whereTo)
            {
                case -1:System.out.println("Exiting... ");
                        System.exit(0);
                        break;
                case 1: p.getPath_taken().add(p.getCurrent_location());
                        p.setCurrent_location( Number_displayed_to_Location.get( location_numbers_to_display.get(availaible_locs.get(1))) ) ;
                        break;
                case 2: p.getPath_taken().add(p.getCurrent_location());
                        p.setCurrent_location( Number_displayed_to_Location.get( location_numbers_to_display.get(availaible_locs.get(2))) ) ;
                        break;
                case 3: p.getPath_taken().add(p.getCurrent_location());
                        p.setCurrent_location( Number_displayed_to_Location.get( location_numbers_to_display.get(availaible_locs.get(3))) ) ;
                        break;
            }

            dec = Fight_monster_at_current_location(p);
            if(dec==1)
            {
                //you won the fight
                //recall this function
                Play_Game(p);
            }
            else if(dec==-1)
            {
                //you Lost the battle
                Display_Startup_menu();
            }
        }

        else
        {
            int dec = 0;

            //The current Location is not the starting location
            //check_location_number and display the appropriate options to the user to choose From
            int check_loc_number = p.getCurrent_location().getLocation_number();
            if(check_loc_number==1 || check_loc_number==2 || check_loc_number==3)
            {
                //user is at location 1, 2 or 3
                System.out.println("You are at location "+check_loc_number+" Choose path:");
                System.out.println("1) Go to location "+location_numbers_to_display.get(availaible_locs.get(4)));
                System.out.println("2) Go to location "+location_numbers_to_display.get(availaible_locs.get(5)));
                System.out.println("3) Go to location "+location_numbers_to_display.get(availaible_locs.get(6)));
                System.out.println("4) Go back");
                System.out.println("Enter -1 to exit");
                int whereTo = Integer.parseInt(sc.nextLine());
                switch(whereTo)
                {
                    //implement the change loc menu
                    case 1: //go to loc 4
                            p.getPath_taken().add(p.getCurrent_location());
                            p.setCurrent_location( Number_displayed_to_Location.get( location_numbers_to_display.get(availaible_locs.get(4))) ) ;
                            break;
                    case 2://go to loc 5
                            p.getPath_taken().add(p.getCurrent_location());
                            p.setCurrent_location( Number_displayed_to_Location.get( location_numbers_to_display.get(availaible_locs.get(5))) ) ;
                            break;
                    case 3://go to loc 6
                            p.getPath_taken().add(p.getCurrent_location());
                            p.setCurrent_location( Number_displayed_to_Location.get( location_numbers_to_display.get(availaible_locs.get(3))) ) ;
                            break;
                    case 4://Implemet GO BACK
                            p.setCurrent_location(p.getPath_taken().get(p.getPath_taken().size()-1));
                            p.getPath_taken().remove(p.getPath_taken().size()-1);
                            break;
                    default:System.out.println("Please Choose a valid option");
                            Play_Game(p);
                            break;
                }
            }



            if(check_loc_number==4 || check_loc_number==5 || check_loc_number==6)
            {
                //user is at location 1, 2 or 3
                System.out.println("You are at location "+check_loc_number+" Choose path:");
                System.out.println("1) Go to location "+location_numbers_to_display.get(availaible_locs.get(7)));
                System.out.println("2) Go to location "+location_numbers_to_display.get(availaible_locs.get(8)));
                System.out.println("3) Go to location "+location_numbers_to_display.get(availaible_locs.get(9)));
                System.out.println("4) Go back");
                System.out.println("Enter -1 to exit");
                int whereTo = Integer.parseInt(sc.nextLine());
                switch(whereTo)
                {
                    //implement the change loc menu
                    case 1: //go to loc 7
                            p.getPath_taken().add(p.getCurrent_location());
                            p.setCurrent_location( Number_displayed_to_Location.get( location_numbers_to_display.get(availaible_locs.get(7))) ) ;
                            break;
                    case 2://go to loc 8
                            p.getPath_taken().add(p.getCurrent_location());
                            p.setCurrent_location( Number_displayed_to_Location.get( location_numbers_to_display.get(availaible_locs.get(8))) ) ;
                            break;
                    case 3://go to loc 9
                            p.getPath_taken().add(p.getCurrent_location());
                            p.setCurrent_location( Number_displayed_to_Location.get( location_numbers_to_display.get(availaible_locs.get(9))) ) ;
                            break;
                    case 4://Implemet GO BACK
                            p.setCurrent_location(p.getPath_taken().get(p.getPath_taken().size()-1));
                            p.getPath_taken().remove(p.getPath_taken().size()-1);
                            break;
                    default:System.out.println("Please Choose a valid option");
                            Play_Game(p);
                            break;
                }
            }

            
            if(check_loc_number==7 || check_loc_number==8 || check_loc_number==9)
            {
                //user is at location 1, 2 or 3
                Random rand = new Random();
                int loc1 = rand.nextInt(10);
                int loc2 = rand.nextInt(10);
                System.out.println("You are at location "+check_loc_number+" Choose path:");
                System.out.println("1) Go to location "+location_numbers_to_display.get(availaible_locs.get(10)));
                System.out.println("2) Go to location "+location_numbers_to_display.get(availaible_locs.get( loc1 )));
                System.out.println("3) Go to location "+location_numbers_to_display.get(availaible_locs.get( loc2 )));
                System.out.println("4) Go back");
                System.out.println("Enter -1 to exit");
                int whereTo = Integer.parseInt(sc.nextLine());
                switch(whereTo)
                {
                    //implement the change loc menu
                    case 1: //go to loc 4
                            p.getPath_taken().add(p.getCurrent_location());
                            p.setCurrent_location( Number_displayed_to_Location.get( location_numbers_to_display.get(availaible_locs.get(10))) ) ;
                            break;
                    case 2://go to loc 5
                            p.getPath_taken().add(p.getCurrent_location());
                            p.setCurrent_location( Number_displayed_to_Location.get( location_numbers_to_display.get(availaible_locs.get(loc1))) ) ;
                            break;
                    case 3://go to loc 6
                            p.getPath_taken().add(p.getCurrent_location());
                            p.setCurrent_location( Number_displayed_to_Location.get( location_numbers_to_display.get(availaible_locs.get(loc2))) ) ;
                            break;
                    case 4://Implemet GO BACK
                            p.setCurrent_location(p.getPath_taken().get(p.getPath_taken().size()-1));
                            p.getPath_taken().remove(p.getPath_taken().size()-1);
                            break;
                    default:System.out.println("Please Choose a valid option");
                            Play_Game(p);
                            break;
                }
            }


            dec = Fight_monster_at_current_location(p);
            if(dec==1)
            {
                //you won the fight
                //recall this function
                Play_Game(p);
            }
            else if(dec==-1)
            {
                //you Lost the battle
                Display_Startup_menu();
            }

        }
    }


    public void Existing_User_login()
    {
        System.out.println("Enter Username");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        boolean user_logged_in = false;
        for( Player p : players )
        {
            if(p.getUserName().equals(userName))
            {
                //print record found and take him to play the game
                System.out.println("User Found... logging in");
                System.out.println("Welcome "+p.getUserName());
                user_logged_in = true;
                if( GameInitialized.get(p)==false )
                {
                    initialize_Engine(p);
                    GameInitialized.put(p, true);
                }
                Play_Game(p);
            }
        }

        if(!user_logged_in)
        {
            System.out.println("No record of such user Found. Exiting");
            //empty feed taken, user record not found, log in again for Existing User
            Display_Startup_menu();
        }

    }

    public void Register_new_user()
    {
        System.out.println("Enter Username");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        System.out.println("Choose a Hero");
        System.out.println("1) Warrior");
        System.out.println("2) Thief");
        System.out.println("3) Mage");
        System.out.println("4) Healer");
        int hero_choice = Integer.parseInt(sc.nextLine());
        //Create a new Player with the apt Hero character
        String Hero_type = null;
        Hero myhero = null;
        Player new_player = null;
        switch(hero_choice)
        {
            case 1://Hero Chosen is Warrior
                     myhero = new Warrior("Warrior");
                     new_player = new Player(username, myhero);
                    players.add(new_player);
                    Hero_type = "Warrior";
                    break;
            case 2://Hero chosen is Thief
                     myhero = new Thief("Thief");
                     new_player = new Player(username, myhero);
                    players.add(new_player);
                    Hero_type = "Thief";
                    break;
            case 3://chosen hero is Mage
                     myhero = new Mage("Mage");
                     new_player = new Player(username, myhero);
                    players.add(new_player);
                    Hero_type = "Mage";
                    break;
            case 4://chosen hero is Healer
                    myhero = new Healer("Healer");
                    new_player = new Player(username, myhero);
                    players.add(new_player);
                    Hero_type = "Healer";
                    break;
        }
        GameInitialized.putIfAbsent(new_player, false);
        System.out.println("User Creation done. Username: "+username+". Hero type: "+Hero_type+". Log in to play the game. Exiting");
        Display_Startup_menu();
    }
    public void start_game()
    {
        Display_Startup_menu();
    }
}

class lab4
{
    public static void main(String[] args) {
        //Create a new gameEngine object
        GameplayEngine myGame = new GameplayEngine();
        myGame.start_game();
    }
}