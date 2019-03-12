----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/12/2018 12:40:01 AM
-- Design Name: 
-- Module Name: ID - Behavioral
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

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity ID is
    Port ( clk : in STD_LOGIC;
            enable: in STD_LOGIC;
           instr : in STD_LOGIC_VECTOR (15 downto 0);
           wd : in STD_LOGIC_VECTOR (15 downto 0);
           RegWrite : in STD_LOGIC;
           RegDest : in STD_LOGIC;
           ExtOp : in STD_LOGIC;
           rd1 : out STD_LOGIC_VECTOR (15 downto 0);
           rd2 : out STD_LOGIC_VECTOR (15 downto 0);
           ext_imm : out STD_LOGIC_VECTOR (15 downto 0);
           func : out STD_LOGIC_VECTOR (2 downto 0);
           sa : out STD_LOGIC);
end ID;

architecture Behavioral of ID is
signal wa:std_logic_vector(2 downto 0);
begin
    C1: entity work.bloc_reg port map(clk,enable,RegWrite,instr(12 downto 10),instr(9 downto 7),wa,wd,rd1,rd2);
    
    wa<=instr(6 downto 4) when RegDest='1'else --poate trebuie modificat
    instr(9 downto 7);
    
    process(ExtOp,instr(6))
    begin
        if ExtOp='0' then
            ext_imm<="000000000" & instr(6 downto 0);
        else
            if instr(6)='0' then
                ext_imm<="000000000" & instr(6 downto 0);
            else
                ext_imm<="111111111" & instr(6 downto 0);
            end if;
        end if;
     end process;
    
    func<=instr(2 downto 0);
    sa<=instr(3);
    
end Behavioral;
