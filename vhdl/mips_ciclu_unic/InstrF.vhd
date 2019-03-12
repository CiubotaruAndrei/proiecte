----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/30/2018 02:39:23 PM
-- Design Name: 
-- Module Name: IF - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/30/2018 02:39:23 PM
-- Design Name: 
-- Module Name: IF - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity IF1 is
    Port ( clk : in STD_LOGIC;
            en: in STD_LOGIC;
            reset:in STD_LOGIC;
           Jadrr : in STD_LOGIC_VECTOR (15 downto 0);
           Badrr : in STD_LOGIC_VECTOR (15 downto 0);
           Jump : in STD_LOGIC;
           PCSrc : in STD_LOGIC;
           Instr : out STD_LOGIC_VECTOR (15 downto 0);
           PCout : out STD_LOGIC_VECTOR (15 downto 0));
end IF1;

architecture Behavioral of IF1 is
type rom is array(0 to 255) of std_logic_vector(15 downto 0);
signal mem_rom:rom:=(
0 => B"001_000_001_0000101", --addi $1 $0 10
1 => B"001_000_010_0000000", --addi $2 $0 0
2 => B"001_000_101_0000010", --addi $5 $0 2
3 => B"000_100_100_100_0_110", --xor $4 $4 $4
4 => B"000_011_011_011_0_110", --xor $3 $3 $3
5 => B"000_100_101_100_0_000", --add $4 $4 $5
6 => B"000_011_100_011_0_000", --add $3 $3 $4
7 => B"001_010_010_0000001", --addi $2 $2 1
8 => B"011_010_011_0000000", --sw $3 0($2)
9 => B"100_010_001_0000001", --beq $1 $2 1
10 => B"111_0000000000101", --j 5
11 => B"010_001_100_0000000", --lw $4 0($1)
12 => B"000_100_000_100_1_011", --srl $4 $4 sa
others => X"A5A5");
signal pc:std_logic_vector(15 downto 0):=(others=>'0'); 
signal mux1:std_logic_vector(15 downto 0);
signal mux2:std_logic_vector(15 downto 0);
begin
    
    process(PCSrc)
    begin
        case PCSrc is
        when '1' => mux1 <= Badrr;
        when others => mux1 <= pc+1;
        end case;
    end process;
    
    process(Jump)
        begin
            case Jump is
            when '0' => mux2 <=mux1;
            when others => mux2 <= Jadrr;
            end case;
        end process;
       
    process(clk)
    begin
    if reset='1' then
        pc<=x"0000";
        else 
            if rising_edge(clk) then
                if en='1' then
                    pc<=mux2;
                end if;
            end if;
     end if;
     end process;
     
     PCout<=pc+1;
     Instr<=mem_rom(conv_integer(pc(7 downto 0)));
    
end Behavioral;



