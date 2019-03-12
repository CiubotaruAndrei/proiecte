----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/19/2018 10:47:56 PM
-- Design Name: 
-- Module Name: EX - Behavioral
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

entity EX is
    Port ( PCOut : in STD_LOGIC_VECTOR (15 downto 0);
           RD1 : in STD_LOGIC_VECTOR (15 downto 0);
           RD2 : in STD_LOGIC_VECTOR (15 downto 0);
           Ext_Imm : in STD_LOGIC_VECTOR (15 downto 0);
           ALUSrc : in STD_LOGIC;
           sa : in STD_LOGIC;
           func : in STD_LOGIC_VECTOR (2 downto 0);
           ALUOp : in STD_LOGIC_VECTOR (2 downto 0);
           Zero : out STD_LOGIC;
           ALURes : out STD_LOGIC_VECTOR (15 downto 0);
           Branch : out STD_LOGIC_VECTOR (15 downto 0));
end EX;

architecture Behavioral of EX is
signal aluin:std_logic_vector(15 downto 0);
signal ALUC:std_logic_vector(2 downto 0);
begin

-- a doua intrare ALU
    process(ALUSrc)
    begin
        case ALUSrc is
        when '0' => aluin <= RD2;
        when others => aluin <= Ext_Imm;
        end case;
   end process;
   
-- calcul branch
    
    Branch<= PCout + Ext_Imm;
    
-- aluControl

    process(ALUOp,func)
    begin
        case ALUOp is
        when "000" =>
            case func is
            when "000" => ALUC <= "000";
            when "001" => ALUC <= "001";
            when "010" => ALUC <= "010";
            when "011" => ALUC <= "011";
            when "100" => ALUC <= "100";
            when "101" => ALUC <= "101";
            when "110" => ALUC <= "110";
            when "111" => ALUC <= "111";
            end case;
       when "001" => ALUC <= "000";
       when "010" => ALUC <= "001";
       when "011" => ALUC <= "100";
       when "100" => ALUC <= "101";-- ori
       when others => ALUC <= "000";
       end case;
    end process;
    
    process(AlUC)
    begin
    Zero<='0';
        case ALUC is
        when "000" => ALURes <= RD1 + aluin;
        when "001" => ALURes <= RD1 - aluin;
             if (RD1- aluin) = X"0000" then
                Zero<='1';
             end if;
        when "010" => 
            if sa='1' then
                ALURes <= RD1(14 downto 0) & '0';
            else
                ALURes <= RD1;
            end if;
        when "011" =>
            if sa='1' then 
                ALURes <= '0' & RD1(15 downto 1);
            else 
                ALURes <= RD1;
            end if;
        when "100" => ALURes <= RD1 and aluin;
        when "101" => ALURes <= RD1 or aluin;
        when "110" => ALURes <= RD1 xor aluin;
        when "111" =>  --sllv imlpem
            case aluin is
                when X"0001" => ALURes <= RD1(14 downto 0) & '0';
                when X"0002" => ALURes <= RD1(13 downto 0) & "00";
                when X"0003" => ALURes <= RD1(12 downto 0) & "000";
                when X"0004" => ALURes <= RD1(11 downto 0) & "0000";
                when X"0005" => ALURes <= RD1(10 downto 0) & "00000";
                when X"0006" => ALURes <= RD1(9 downto 0) & "000000";
                when X"0007" => ALURes <= RD1(8 downto 0) & "0000000";
                when X"0008" => ALURes <= RD1(7 downto 0) & "00000000";
                when X"0009" => ALURes <= RD1(6 downto 0) & "000000000";
                when X"000A" => ALURes <= RD1(5 downto 0) & "0000000000";
                when X"000B" => ALURes <= RD1(4 downto 0) & "00000000000";
                when X"000C" => ALURes <= RD1(3 downto 0) & "000000000000";
                when X"000D" => ALURes <= RD1(2 downto 0) & "0000000000000";
                when X"000E" => ALURes <= RD1(1 downto 0) & "00000000000000";
                when X"000F" => ALURes <= RD1(0) & "000000000000000";
                when others => ALURes <= X"0000";
            end case;
        end case;
    end process;
            
end Behavioral;
