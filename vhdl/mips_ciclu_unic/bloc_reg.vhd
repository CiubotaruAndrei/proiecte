----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/16/2018 03:06:35 PM
-- Design Name: 
-- Module Name: bloc_reg - Behavioral
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

entity bloc_reg is
    Port ( clk : in STD_LOGIC;
            enable: in STD_LOGIC;
           wr : in STD_LOGIC;
           ra1 : in STD_LOGIC_VECTOR (2 downto 0);
           ra2 : in STD_LOGIC_VECTOR (2 downto 0);
           wa : in STD_LOGIC_VECTOR (2 downto 0);
           wd : in STD_LOGIC_VECTOR (15 downto 0);
           rd1 : out STD_LOGIC_VECTOR (15 downto 0);
           rd2 : out STD_LOGIC_VECTOR (15 downto 0));
end bloc_reg;

architecture Behavioral of bloc_reg is
type reg_array is array (0 to 7)of std_logic_vector(15 downto 0);
signal reg_file : reg_array:=(0 => X"0000",
others => X"0000");

begin
    
    process(clk)
    begin
    if rising_edge(clk) then
        if enable='1' then
            if wr='1' then
                reg_file(conv_integer(wa))<=wd;
            end if;
        end if;
    end if;
    end process;
    
    rd1 <= reg_file(conv_integer(ra1));
    rd2 <= reg_file(conv_integer(ra2));
    
end Behavioral;
