package cpu.opcodes

import cpu.Operation
import BitManipulation as bm

// 8-Bit Loads
var loads8Bit = mapOf(

  0x06 to Operation("LD B,n", 1, 8, {r, _, a -> r.B = a[0]}),
  0x0E to Operation("LD C,n", 1, 8, {r, _, a -> r.C = a[0]}),
  0x16 to Operation("LD D,n", 1, 8, {r, _, a -> r.D = a[0]}),
  0x1E to Operation("LD E,n", 1, 8, {r, _, a -> r.E = a[0]}),
  0x26 to Operation("LD H,n", 1, 8, {r, _, a -> r.H = a[0]}),
  0x2E to Operation("LD L,n", 1, 8, {r, _, a -> r.L = a[0]}),

  0x78 to Operation("LD A,B", 0, 4, {r, _, _ -> r.A = r.B}),
  0x79 to Operation("LD A,C", 0, 4, {r, _, _ -> r.A = r.C}),
  0x7A to Operation("LD A,D", 0, 4, {r, _, _ -> r.A = r.D}),
  0x7B to Operation("LD A,E", 0, 4, {r, _, _ -> r.A = r.E}),
  0x7C to Operation("LD A,H", 0, 4, {r, _, _ -> r.A = r.H}),
  0x7D to Operation("LD A,L", 0, 4, {r, _, _ -> r.A = r.L}),
  0x0A to Operation("LD A,(BC)", 0, 8, {r, m, _ -> r.A = m.readByte(r.BC)}),
  0x1A to Operation("LD A,(DE)", 0, 8, {r, m, _ -> r.A = m.readByte(r.DE)}),
  0x7E to Operation("LD A,(HL)", 0, 8, {r, m, _ -> r.A = m.readByte(r.HL)}),
  0x3E to Operation("LD A, n", 1, 8, {r, _, a -> r.A = a[0]}),
  0xFA to Operation("LD A,(nn)", 2, 16, {r, m, a -> r.A = m.readByte(bm.argsToWord(a))}),

  0x40 to Operation("LD B,B", 0, 4, {r, _, _ -> r.B = r.B}),
  0x41 to Operation("LD B,C", 0, 4, {r, _, _ -> r.B = r.C}),
  0x42 to Operation("LD B,D", 0, 4, {r, _, _ -> r.B = r.D}),
  0x43 to Operation("LD B,E", 0, 4, {r, _, _ -> r.B = r.E}),
  0x44 to Operation("LD B,H", 0, 4, {r, _, _ -> r.B = r.H}),
  0x45 to Operation("LD B,L", 0, 4, {r, _, _ -> r.B = r.L}),
  0x46 to Operation("LD B,(HL)", 0, 8, {r, m, _ -> r.B = m.readByte(r.HL)}),

  0x48 to Operation("LD C,B", 0, 4, {r, _, _ -> r.C = r.B}),
  0x49 to Operation("LD C,C", 0, 4, {r, _, _ -> r.C = r.C}),
  0x4A to Operation("LD C,D", 0, 4, {r, _, _ -> r.C = r.D}),
  0x4B to Operation("LD C,E", 0, 4, {r, _, _ -> r.C = r.E}),
  0x4C to Operation("LD C,H", 0, 4, {r, _, _ -> r.C = r.H}),
  0x4D to Operation("LD C,L", 0, 4, {r, _, _ -> r.C = r.L}),
  0x4E to Operation("LD C,(HL)", 0, 8, {r, m, _ -> r.C = m.readByte(r.HL)}),

  0x50 to Operation("LD D,B", 0, 4, {r, _, _ -> r.D = r.B}),
  0x51 to Operation("LD D,C", 0, 4, {r, _, _ -> r.D = r.C}),
  0x52 to Operation("LD D,D", 0, 4, {r, _, _ -> r.D = r.D}),
  0x53 to Operation("LD D,E", 0, 4, {r, _, _ -> r.D = r.E}),
  0x54 to Operation("LD D,H", 0, 4, {r, _, _ -> r.D = r.H}),
  0x55 to Operation("LD D,L", 0, 4, {r, _, _ -> r.D = r.L}),
  0x56 to Operation("LD D,(HL)", 0, 8, {r, m, _ -> r.D = m.readByte(r.HL)}),

  0x58 to Operation("LD E,B", 0, 4, {r, _, _ -> r.E = r.B}),
  0x59 to Operation("LD E,C", 0, 4, {r, _, _ -> r.E = r.C}),
  0x5A to Operation("LD E,D", 0, 4, {r, _, _ -> r.E = r.D}),
  0x5B to Operation("LD E,E", 0, 4, {r, _, _ -> r.E = r.E}),
  0x5C to Operation("LD E,H", 0, 4, {r, _, _ -> r.E = r.H}),
  0x5D to Operation("LD E,L", 0, 4, {r, _, _ -> r.E = r.L}),
  0x5E to Operation("LD E,(HL)", 0, 8, {r, m, _ -> r.E = m.readByte(r.HL)}),

  0x60 to Operation("LD H,B", 0, 4, {r, _, _ -> r.H = r.B}),
  0x61 to Operation("LD H,C", 0, 4, {r, _, _ -> r.H = r.C}),
  0x62 to Operation("LD H,D", 0, 4, {r, _, _ -> r.H = r.D}),
  0x63 to Operation("LD H,E", 0, 4, {r, _, _ -> r.H = r.E}),
  0x64 to Operation("LD H,H", 0, 4, {r, _, _ -> r.H = r.H}),
  0x65 to Operation("LD H,L", 0, 4, {r, _, _ -> r.H = r.L}),
  0x66 to Operation("LD H,(HL)", 0, 8, {r, m, _ -> r.H = m.readByte(r.HL)}),

  0x68 to Operation("LD L,B", 0, 4, {r, _, _ -> r.L = r.B}),
  0x69 to Operation("LD L,C", 0, 4, {r, _, _ -> r.L = r.C}),
  0x6A to Operation("LD L,D", 0, 4, {r, _, _ -> r.L = r.D}),
  0x6B to Operation("LD L,E", 0, 4, {r, _, _ -> r.L = r.E}),
  0x6C to Operation("LD L,H", 0, 4, {r, _, _ -> r.L = r.H}),
  0x6D to Operation("LD L,L", 0, 4, {r, _, _ -> r.L = r.L}),
  0x6E to Operation("LD L,(HL)", 0, 8, {r, m, _ -> r.L = m.readByte(r.HL)}),

  0x70 to Operation("LD (HL),B", 0, 8, {r, m, _ -> m.writeByte(r.HL, r.B)}),
  0x71 to Operation("LD (HL),C", 0, 8, {r, m, _ -> m.writeByte(r.HL, r.C)}),
  0x72 to Operation("LD (HL),D", 0, 8, {r, m, _ -> m.writeByte(r.HL, r.D)}),
  0x73 to Operation("LD (HL),E", 0, 8, {r, m, _ -> m.writeByte(r.HL, r.E)}),
  0x74 to Operation("LD (HL),H", 0, 8, {r, m, _ -> m.writeByte(r.HL, r.H)}),
  0x75 to Operation("LD (HL),L", 0, 8, {r, m, _ -> m.writeByte(r.HL, r.L)}),
  // 36 is not a typo
  0x36 to Operation("LD (HL),n", 1, 12, {r, m, a -> m.writeByte(r.HL, a[0])}),

  0x7F to Operation("LD A,A", 0, 4, {r, _, _ -> r.A = r.A}),
  0x47 to Operation("LD B,A", 0, 4, {r, _, _ -> r.B = r.A}),
  0x4F to Operation("LD C,A", 0, 4, {r, _, _ -> r.C = r.A}),
  0x57 to Operation("LD D,A", 0, 4, {r, _, _ -> r.D = r.A}),
  0x5F to Operation("LD E,A", 0, 4, {r, _, _ -> r.E = r.A}),
  0x67 to Operation("LD H,A", 0, 4, {r, _, _ -> r.H = r.A}),
  0x6F to Operation("LD L,A", 0, 4, {r, _, _ -> r.L = r.A}),
  0x02 to Operation("LD (BC),A", 0, 8, {r, m, _ -> m.writeByte(r.BC, r.A)}),
  0x12 to Operation("LD (DE),A", 0, 8, {r, m, _ -> m.writeByte(r.DE, r.A)}),
  0x77 to Operation("LD (HL),A", 0, 8, {r, m, _ -> m.writeByte(r.HL, r.A)}),
  0xEA to Operation("LD (nn),A", 2, 16, {r, m, a -> m.writeByte(bm.argsToWord(a), r.A)}),

  0xF2 to Operation("LD A,(C)", 0, 8, {r, m, _ -> r.A = m.readByte(0xFF00 + r.C)}),
  0xE2 to Operation("LD (C),A", 0, 8, {r, m, _ -> m.writeByte(0xFF00 + r.C, r.A)}),

  0x3A to Operation("LDD A,(HL)", 0, 8, {r, m, _ -> r.A = m.readByte(r.getAndDecrementHL())}),
  0x32 to Operation("LDD (HL),A", 0, 8, {r, m, _ -> m.writeByte(r.getAndDecrementHL(), r.A)}),

  0x2A to Operation("LDI A,(HL)", 0, 8, {r, m, _ -> r.A = m.readByte(r.getAndIncrementHL())}),
  0x22 to Operation("LDI (HL),A", 0, 8, {r, m, _ -> m.writeByte(r.getAndIncrementHL(), r.A)}),

  0xE0 to Operation("LD (n),A", 1, 8, {r, m, a -> m.writeByte(0xFF00 + a[0], r.A)}),
  0xF0 to Operation("LD A,(n)", 1, 8, {r, m, a -> r.A = m.readByte(0xFF00 + a[0])})



)