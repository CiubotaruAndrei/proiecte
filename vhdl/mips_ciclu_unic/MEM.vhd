----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/19/2018 10:27:51 PM
-- Design Name: 
-- Module Name: MEM - Behavioral
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

entity MEM is
    Port ( clk : in STD_LOGIC;
           enable : in STD_LOGIC;
           MemWrite : in STD_LOGIC;
           ALURes : in STD_LOGIC_VECTOR (15 downto 0);
           RD2 : in STD_LOGIC_VECTOR (15 downto 0);
           MemData : out STD_LOGIC_VECTOR (15 downto 0);
           ALUOut : out STD_LOGIC_VECTOR (15 downto 0));
end MEM;

architecture Behavioral of MEM is
type ram is array(0 to 31) of std_logic_vector(15 downto 0);
signal mem_ram:ram:=(0=>X"0000",
others=>"0000");

begin

    process(clk)
    begin
        if rising_edge(clk) then
            if enable='1' then
                if MemWrite='1' then
                    mem_ram(conv_integer(ALURes))<=RD2;
                end if;
            end if;    
        end if;
    end process;
   MemData<=mem_ram(conv_integer(ALURes));
   ALUOut<=ALURes;

end Behavioral;
